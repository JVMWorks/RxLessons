package reactive.prog.rxjava.book.oreilly.ch2;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;
import rx.Observer;

import static reactive.prog.rxjava.book.oreilly.services.TwitterService.fetchTweets;

public class Ex1_Observer {
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
        tweets.subscribe(observer);

        //Note: There is no way for this observer to stop observing..
    }
    private static void noMore() {
        //Done
        System.out.println("Done!..");
    }
}
