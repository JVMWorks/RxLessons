package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;
import rx.Subscriber;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.fetchTweets;

// Mastering Observable.create()
// Interestingly, even though RxJava is all about asynchronous processing of streams of events,
// the Observable's create factory methods by default operate on the client thread.
public class Ex4_ObservableExecOnSameClientThreadOnly {
    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        log("Before");
        Observable.range(5, 3)
                .subscribe(i -> log(i));
        log("After");

        /* o/p:
        * main: Before
        * main: 5
        * main: 6
        * main: 7
        * main: After
        */
        // Notice that subscription also happened in the client thread (i.e. main) and
        // subscribe() actually blocked the client thread until all events were received.
        // Unless required by some operator RxJava does not implicitly run your code in any thread pool.
    }
}
