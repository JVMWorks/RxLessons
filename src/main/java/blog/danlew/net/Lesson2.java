package blog.danlew.net;

import rx.Observable;
import rx.functions.Action1;

// Ref.: http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
// Simpler version of Lesson1
public class Lesson2 {

    public static void main(String[] args) {
        // Our Observable emits "Hello, world!" then completes.
        // RxJava has multiple built-in Observable creation methods for common tasks.
        // In this case, Observable.just() emits a single item then completes.
        Observable<String> myObservable = Observable.just("Hello World!");

        // Create a Subscriber to consume the data:
        // Next, let's handle that unnecessarily verbose Subscriber.
        // We don't care about onCompleted() nor onError(),
        // so instead we can use a simpler class to define what to do during onNext()
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                // All this does is print each String emitted by the Observable.
                System.out.println(s);
            }
        };

        // Now that we've got myObservable and onNextAction.
        // We can hook them up to each other using subscribe():
        // Actions can define each part of a Subscriber. Observable.subscribe() like below:
        // myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
        // However, we only need the first parameter, because we're ignoring onError() and onComplete():
        myObservable.subscribe(onNextAction);

    }
}
