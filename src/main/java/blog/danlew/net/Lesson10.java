package blog.danlew.net;

import blog.danlew.net.errors.MyExceptionType;
import blog.danlew.net.util.MockMoviesAPI;
import org.apache.commons.lang3.text.WordUtils;
import rx.Subscriber;
import rx.exceptions.Exceptions;

// Ref.: http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/
// Objective: Understanding Error Handling - Checked Exception
public class Lesson10 {
    public static void main(String[] args) {
        MockMoviesAPI.findMovieURLsByType("comedy")
                .flatMapIterable(urls -> urls) // flatMapIterable (transform your list to an Observable of items)
                .flatMap(url -> MockMoviesAPI.findMovieTitleFromURL(url))
                .map(movie -> WordUtils.capitalizeFully(movie))
                .map(s -> {
                    try {
                        return potentialCheckedException(s);
                    } catch (MyExceptionType ex) {
                        // Convenience method to throw a RuntimeException and Error directly or
                        // wrap any other exception type into a RuntimeException.
                        throw Exceptions.propagate(ex);  //throw not return
                    }
                })
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

    private static String potentialCheckedException(final String s) throws MyExceptionType {
        final long randomNumber = Math.round(Math.random());
        if ( randomNumber % 3 == 0) throw new MyExceptionType();
        return s;
    }

}
