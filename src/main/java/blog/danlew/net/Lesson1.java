package blog.danlew.net;

import rx.Observable;
import rx.Subscriber;

// Ref.: http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
// The basic building blocks of reactive code are Observables and Subscribers
// An Observable emits items; a Subscriber consumes those items.
// The smallest building block is actually an Observer,
// but in practice you are most often using Subscriber because that's how you hook up to Observables

// An Observable may emit any number of items (including zero items),
// then it terminates either by successfully completing, or due to an error.
public class Lesson1 {

    public static void main(String[] args) {
        // Our Observable emits "Hello, world!" then completes.
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    // For each Subscriber it has, an Observable calls Subscriber.onNext() any number of times,
                    // followed by either Subscriber.onComplete() or Subscriber.onError().
                    // This differs from Observer Pattern in that this doesn't start emitting items
                    // until someone explicitly subscribes to them
                    // The term used is "hot" vs. "cold" Observables.
                    // A HOT Observable emits items all the time, even when no one is listening.
                    // A COLD Observable only emits items when it has a subscriber (and is what I'm using in all my examples).
                    // This distinction isn't that important to initially learning RxJava.
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello World!");
                        subscriber.onCompleted();
                    }
                }
        );

        // Create a Subscriber to consume the data:
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(final Throwable e) {}

            @Override
            public void onNext(final String s) {
                // All this does is print each String emitted by the Observable.
                System.out.println(s);
            }
        };

        // Now that we've got myObservable and mySubscriber.
        // We can hook them up to each other using subscribe():
        // When the subscription is made, myObservable calls the subscriber's onNext() and onComplete() methods.
        myObservable.subscribe(mySubscriber);

    }
}
