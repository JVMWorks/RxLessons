package blog.danlew.net.services;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

// Ref: http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
// Ref: http://reactivex.io/documentation/operators/from.html
public class MockMoviesAPI {
    public static Observable<List<String>> findMovieTitlesHaving(String word) {
        List<String> result = Arrays.asList(
                "Winners don't stop trying",
                "Losers crib",
                "Gamers dodge",
                "Players party",
                "Catchers hunt",
                "Boxers fight",
                "Jokers crack jokes");
        return Observable.just(result);
    }

    public static Observable<List<String>> findMovieURLsByType(final String genre) {
        List<String> result = Arrays.asList(
                "http://www.imdb.com/title/tt3469046/?ref_=adv_li_tt", //Despicable Me 3
                "http://www.imdb.com/title/tt4765284/?ref_=adv_li_tt", //Pitch Perfect 3
                "http://www.imdb.com/title/tt3521164/?ref_=adv_li_tt", //Moana
                "http://www.imdb.com/title/tt3606752/?ref_=adv_li_tt", //Cars 3
                "http://www.imdb.com/title/tt1469304/?ref_=adv_li_tt", //Baywatch
                "http://www.imdb.com/title/tt4481514/?ref_=adv_li_tt", //The House
                "http://www.imdb.com/title/tt1679335/?ref_=adv_li_tt", //Trolls
                "http://www.imdb.com/title/tt4116284/?ref_=adv_li_tt", //The Lego Batman Movie
                "http://www.imdb.com/title/tt4799050/?ref_=adv_li_tt", //Rough Night
                "http://www.imdb.com/title/tt0493405/?ref_=adv_li_tt" //Chips
        );
        return Observable.just(result);
    }

    public static Observable<String> findMovieTitleFromURL(final String url) {
        return Observable.just("random movie title");
    }
}
