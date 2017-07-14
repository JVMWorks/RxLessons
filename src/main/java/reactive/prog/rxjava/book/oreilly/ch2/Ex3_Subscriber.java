package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.fetchTweets;

public class Ex3_Subscriber {
    public static void main(String[] args) {
        Subscriber<Tweet> subscriber = new Subscriber<Tweet>() {
            @Override
            public void onNext(Tweet tweet) {
                System.out.println(tweet);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                unsubscribe();
            }

            @Override
            public void onCompleted() {
                unsubscribe();
            }
        };

        Observable<Tweet> tweets = fetchTweets();
        tweets.subscribe(subscriber);

        // Lesson:
        // Subscription is a way to control subscription outside of the Observer or callback.
        // Subscriber<T>, on the other hand, implements both Observer<T> and Subscription.
        // Thus, it can be used both to consume notifications (events, completions, or errors) and control subscription.
        // For eg.: a Subscriber can receive only the first n events and then unsubscribe().
    }
}
