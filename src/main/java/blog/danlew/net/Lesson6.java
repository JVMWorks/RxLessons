package blog.danlew.net;

import blog.danlew.net.util.MockMoviesAPI;
import org.apache.commons.lang3.text.WordUtils;
import rx.Observable;

// Ref.: http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
// Objective: I want to make a robust system for searching text and displaying the results in the desired format.
public class Lesson6 {
    public static void main(String[] args) {
        // Option 1: I lose the flexibility to transform the data stream.
        MockMoviesAPI.findMovieTitlesHaving("search text")
                .subscribe(titles -> {
                    for (String title : titles) {
                        System.out.println(WordUtils.capitalizeFully(title));
                    }
                });


        System.out.println("-----------");
        // Option 2: Using Observable.from(), that takes a collection of items and emits each them one at a time
        // Observable.from("url1", "url2", "url3").subscribe(url -> System.out.println(url));
        // I've got multiple, nested subscriptions now! It's now ugly and hard to modify.
        // Ugh, it also breaks some critical as-yet undiscovered features of RxJava. The way RxJava does error handling, threading, and subscription cancellation wouldn't work at all with this code.
        MockMoviesAPI.findMovieTitlesHaving("search text")
                .subscribe(movies -> {
                    rx.Observable.from(movies)
                            .map(movie -> WordUtils.capitalizeFully(movie))
                            .subscribe(title -> System.out.println(title));
                });

        
        System.out.println("-----------");
        // Option 3: Doing the transformation using required Transformation Operators
        // Every map() call would have a for-each loop inside of it - Ouch, that hurts!
        MockMoviesAPI.findMovieTitlesHaving("search text")
                .flatMap(movies -> Observable.from(movies))
                .map(movie -> WordUtils.capitalizeFully(movie)) // map (transform your item to another item)
                .subscribe(title -> System.out.println(title));

        // flatMap() is weird, right? Why is it returning another Observable?
        // The key concept here is that the new Observable returned is what the Subscriber sees.
        // Note that the subscribe doesn't receive a List<String> - it gets a series of individual Strings as returned by Observable.from().

        // Notice the Map transformation to Capitalise the Movie Title. Crazy, right?
        // I'm composing multiple independent methods returning Observables together! How cool is that!

        System.out.println("-----------");
        //Option 4: Further simplifying Option3
        MockMoviesAPI.findMovieTitlesHaving("search text")
                .flatMapIterable(movies -> movies) // flatMapIterable (transform your list to an Observable of items)
                .map(movie -> WordUtils.capitalizeFully(movie))
                .subscribe(title -> System.out.println(title));

    } //End-of-psvm
}
