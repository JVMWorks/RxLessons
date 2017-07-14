package reactive.prog.rxjava.book.oreilly.ch2;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;

// Objective: Is it possible to exec subscriptions in a non-blocking way?
public class Ex8_BadNonBlockingInfiniteStreamSubscription {
    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {

        // Lesson: For non-blocking event streaming, it has to happen in a separate non-blocking thread
        // Food For Thought:
        // Handling unsubscription immediately before trying to send an event is fine as long as
        // events are pushed relatively often. But imagine a situation in which events appear very rarely.
        // Observable can only determine that a subscriber unsubscribed when it attempts to push some event to it.
        // If the subscriber decides to unsubscribe before an event is emitted,
        // the resources are held idly for that extra duration of time until the
        // `while ( !subscriber.isUnsubscribed())` check is made.
        // This approach is sub-optimal.

        // Luckily, with a subscriber instance we can be notified as soon as it unsubscribes,
        // cleaning up resources as soon as possible, not when the next message appears
        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;
                        // For every iteration we make, we need to ensure that someone is actually listening.
                        while ( !subscriber.isUnsubscribed()) {
                            log(" Inside Thread Loop");
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);
                        }
                    };
                    Thread thread = new Thread(r);
                    thread.start();
                    // The background thread is already running.
                    // But just after spawning a thread, we ask the subscriber to let us know
                    // by invoking a callback if it unsubscribes and is registered via Subscriber.add().
                    // This callback has basically a single purpose: to interrupt a thread.
                    // What calling Thread.interrupt() does is throw an InterruptedException inside sleep()
                    // sleep() exits gracefully after swallowing the exception.
                    // You can use the same pattern to perform any cleanup. However, if your stream produces a steady,
                    // frequent flow of events, you can probably live without explicit callback.
                    subscriber.add( //Subscription s
                            Subscriptions.create(thread::interrupt)
                    );
                }
        );
        Subscription subs1 = naturalNumbers.subscribe(x -> log("Subscription 1 : " + x));
        killtime();
        Subscription subs2 = naturalNumbers.subscribe(x -> log("Subscription 2 : " + x));
        killtime();
        subs1.unsubscribe();
        subs2.unsubscribe();

        // Lesson:
        // There is another reason why you should NOT USE explicit THREADS inside create().
        // The Rx Design Guidelines in section 4.2.
        // Assume observer instances are called in a serialized fashion require that
        // subscribers never receive notifications concurrently.
        // It is easy to violate this requirement when explicit threads are involved.
        // Such an assumption allows writing Observers as if they were synchronized,
        // always accessed by at most one thread.
    }

    private static void killtime() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
