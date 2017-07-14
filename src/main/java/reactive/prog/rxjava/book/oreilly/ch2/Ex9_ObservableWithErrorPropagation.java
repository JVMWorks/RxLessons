package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.load;


public class Ex9_ObservableWithErrorPropagation {

    // We’ve learned so far that Observer<T> can receive values of type T,
    // optionally followed by either completion or error.
    // But how do you push errors downstream to all subscribers?
    // It is a good practice to wrap entire expressions within create() in a try-catch block.
    // Throwables should be propagated downstream rather than logged or rethrown, as demonstrated here:
    // Making Observable from result of method
    public static Observable<Tweet> rxLoad(int id) {
        return Observable.create(subscriber -> {
            try {
                // This extra try-catch block is necessary to propagate the possible Exception
                // thrown from, for example, load(id).
                subscriber.onNext(load(id));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    public static void main(String[] args) {
        rxLoad(1).subscribe(s -> System.out.println(s));
    }
}
