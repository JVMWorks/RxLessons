package blog.danlew.net;

import blog.danlew.net.services.MockMoviesAPI;
import org.apache.commons.lang3.text.WordUtils;
import rx.Subscriber;

// Ref.: http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/
// Objective: Understanding Error Handling
public class Lesson8 {
    public static void main(String[] args) {
        System.out.println("-----------");
        //Option 1: Adding Error Handling Mechanisms in Subscriber
        MockMoviesAPI.findMovieURLsByType("comedy")
                .flatMapIterable(urls -> urls) // flatMapIterable (transform your list to an Observable of items)
                .flatMap(url -> MockMoviesAPI.findMovieTitleFromURL(url))
                .map(movie -> WordUtils.capitalizeFully(movie))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------Completed!-------");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.out.println("------Oops, an error occured!-------");
                        System.out.println(e.getStackTrace());
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println(s);
                    }
                });
    } //End-of-psvm
}
