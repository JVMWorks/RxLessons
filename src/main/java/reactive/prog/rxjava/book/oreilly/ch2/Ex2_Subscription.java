package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.fetchTweets;

public class Ex2_Subscription {
    public static void main(String[] args) {
        Observer<Tweet> observer = new Observer<Tweet>() {
            @Override
            public void onNext(Tweet tweet) {
                System.out.println(tweet);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onCompleted() {
                noMore();
            }
        };

        Observable<Tweet> tweets = fetchTweets();
        Subscription subscription = tweets.subscribe(observer);
        // ..
        subscription.unsubscribe();

        // Lesson:
        // Subscription is a handle that allows client code to cancel a subscription by using the unsubscribe()method.
        // It is important to unsubscribe from Observable<T> as soon as you no longer want to receive more events;
        // this avoids memory leaks and unnecessary load on the system.
        // Subscription is a way to control subscription outside of the Observer or callback.
    }
    private static void noMore() {
        //Done
        System.out.println("Done!..");
    }
}
