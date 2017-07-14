package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.load;


public class Ex10_ObservableWithErrorPropagationSimplerWay {

    // We’ve learned so far that Observer<T> can receive values of type T,
    // optionally followed by either completion or error.
    // But how do you push errors downstream to all subscribers?
    // It is a good practice to wrap entire expressions within create() in a try-catch block.
    // Throwables should be propagated downstream rather than logged or rethrown, as demonstrated here:
    // Making Observable from result of method
    public static Observable<Tweet> rxLoad(int id) {
        // This is semantically equivalent but much shorter
        // and has some other benefits over create()
        return Observable.fromCallable(
                () -> load(id)
        );
    }

    public static void main(String[] args) {
        rxLoad(1).subscribe(s -> System.out.println(s));
    }
}
