package reactive.prog.rxjava.book.oreilly.ch2;

import rx.Observable;

public class Ex6_MultiSubscribersWithCache {
    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        // In output: You should notice that "create" is printed with every subscription.
        // Keep in mind: Every time subscribe() is called, our subscription handler inside create() is invoked.
        // In some cases, the fact that every subscriber gets its own unique handler invocation works great.
        // On the other hand, if you put a database query or heavyweight computation inside create(),
        // it might be beneficial to share a single invocation among all subscribers.
        // How do we achieve this?
        // If you'd like to avoid calling create() for each subscriber and reuse events that were already computed,
        // there exists a handy cache() operator:
        // It wraps existing Observables, enhancing them, typically by intercepting subscription.
        // When the first subscriber appears, cache() delegates subscription to the underlying Observable and
        // forwards all notifications (events, completions, or errors) downstream. However, at the same time,
        // it keeps a copy of all notifications internally.
        // When a subsequent subscriber wants to receive pushed notifications,
        // cache() no longer delegates to the underlying Observable but instead feeds cached values.
        // CAUTION: cache() plus infinite stream results in  OutOfMemoryError.

        Observable<Integer> ints = Observable.<Integer>create(
                subscriber -> {
                    log("Create..");
                    subscriber.onNext(100);
                    subscriber.onCompleted();
                }
        ).cache();

        log("Starting..to understand Multi-Subscription");
        ints.subscribe(i -> log(i));
        ints.subscribe(i -> log(i));
        log("Ending..to understand Multi-Subscription");
    }
}
