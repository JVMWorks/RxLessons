package reactive.prog.rxjava.book.oreilly.ch2;

import rx.Observable;
import rx.Subscription;

import java.math.BigInteger;

// Objective: Is it possible to exec subscriptions in a non-blocking way?
public class Ex7_NonBlockingInfiniteStreamSubscription {
    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;
                        // For every iteration we make, we need to ensure that someone is actually listening.
                        while ( !subscriber.isUnsubscribed()) {
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);
                        }
                    };
                    // CAUTION: Even though there is nothing inherently wrong with spawning your own threads within create(),
                    // it is error prone and scales poorly.
                    // RxJava provides options for declarative concurrency and custom schedulers
                    // that allow you to write concurrent code without really interacting with threads yourself.
                    new Thread(r).start();
                }
        );
        Subscription subs1 = naturalNumbers.subscribe(x -> log("Subscription 1 : " + x));
        killtime();
        Subscription subs2 = naturalNumbers.subscribe(x -> log("Subscription 2 : " + x));
        killtime();
        subs1.unsubscribe();
        subs2.unsubscribe();

        // Lesson: For non-blocking event streaming, it has to happen in a separate non-blocking thread

        // Food For Thought:
        // Handling unsubscription immediately before trying to send an event is fine as long as
        // events are pushed relatively often. But imagine a situation in which events appear very rarely.
        // Observable can only determine that a subscriber unsubscribed when it attempts to push some event to it.
        // If the subscriber decides to unsubscribe before an event is emitted,
        // the resources are held idly for that extra duration of time until the
        // `while ( !subscriber.isUnsubscribed())` check is made.
        // This approach is sub-optimal.

    }

    private static void killtime() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
