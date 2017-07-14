package reactive.prog.rxjava.book.oreilly.ch2;

import rx.Observable;

public class Ex5_MultiSubscribers {
    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        Observable<Integer> ints = Observable.create(
                subscriber -> {
                    log("Create..");
                    subscriber.onNext(100);
                    subscriber.onCompleted();
                }
        );

        log("Starting..to understand Multi-Subscription");
        ints.subscribe(i -> log(i));
        ints.subscribe(i -> log(i));
        log("Ending..to understand Multi-Subscription");

        // In output: You should notice that "create" is printed with every subscription.
        // Keep in mind: Every time subscribe() is called, our subscription handler inside create() is invoked.
        // In some cases, the fact that every subscriber gets its own unique handler invocation works great.
        // On the other hand, if you put a database query or heavyweight computation inside create(),
        // it might be beneficial to share a single invocation among all subscribers.
    }
}
