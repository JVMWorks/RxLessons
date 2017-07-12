package blog.danlew.net;

import blog.danlew.net.services.MockMoviesAPI;
import org.apache.commons.lang3.text.WordUtils;
import rx.Subscriber;

// Ref.: http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/
// Objective: Understanding Error Handling - Runtime Exception
public class Lesson9 {
    public static void main(String[] args) {
        MockMoviesAPI.findMovieURLsByType("comedy")
                .flatMapIterable(urls -> urls) // flatMapIterable (transform your list to an Observable of items)
                .flatMap(url -> MockMoviesAPI.findMovieTitleFromURL(url))
                .map(movie -> WordUtils.capitalizeFully(movie))
                .map(s -> potentialRuntimeException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------Completed!-------");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        System.out.print("------Oops, an error occured!-------");
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println(s);
                    }
                });
    } //End-of-psvm

    private static String potentialRuntimeException(final String s) {
        final long randomNumber = Math.round(Math.random());
        if ( randomNumber % 5 == 0) throw new RuntimeException("RandomRuntimeException");
        return s;
    }

}
