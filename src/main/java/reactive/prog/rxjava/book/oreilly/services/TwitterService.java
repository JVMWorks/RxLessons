package reactive.prog.rxjava.book.oreilly.services;

import reactive.prog.rxjava.book.oreilly.models.Tweet;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {
    public static Observable<Tweet> fetchTweets() {
        List<Tweet> tweets = new ArrayList<>();
        return Observable.from(tweets);
    }

    public static Tweet load(int id) {
        return new Tweet(id);
    }
}
