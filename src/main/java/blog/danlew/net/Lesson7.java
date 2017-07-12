package blog.danlew.net;

import blog.danlew.net.services.MockMoviesAPI;
import org.apache.commons.lang3.text.WordUtils;

// Ref.: http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
// Objective: I want to make a robust system for searching text and displaying the results in the desired format.
// The Challenge: Get the Movie title from each of the returned URL result
public class Lesson7 {
    public static void main(String[] args) {
        // flatMap() is weird, right? Why is it returning another Observable?
        // The key concept here is that the new Observable returned is what the Subscriber sees.
        // Note that the subscribe doesn't receive a List<String> - it gets a series of individual Strings as returned by Observable.from().

        // Notice the Map transformation to Capitalise the Movie Title. Crazy, right?
        // I'm composing multiple independent methods returning Observables together! How cool is that!

        System.out.println("-----------");
        //Option 4: Further simplifying Option3
        MockMoviesAPI.findMovieURLsByType("comedy")
                .flatMapIterable(urls -> urls) // flatMapIterable (transform your list to an Observable of items)
                .flatMap(url -> MockMoviesAPI.findMovieTitleFromURL(url))
                .map(movie -> WordUtils.capitalizeFully(movie))
                .subscribe(title -> System.out.println(title));

        // Notice how I'm combining two API calls (MockMoviesAPI.findMovieURLsByType and MockMoviesAPI.findMovieTitleFromURL) into a single chain.
        // We could do it for any number of API calls.
        // Imagine how you'd go about doing it the Async Callbacks way. You know how much of a pain in the ass it is to keep all your API calls synced, having to link their callbacks together before presenting the data?
        // We've skipped the trip to callback hell; all that same logic is now encased in this short reactive call.
        // You may be wondering about the other part of callback hell that is error handling. We'll get there later.

        // The other Transformation Operators
        // filter() emits the same item it received, but only if it passes the boolean check
        // take(number) emits, at most, the number of items specified. (If there are fewer than said #, it'll just stop early.)
        // doOnNext() allows us to add extra behavior each time an item is emitted, in this case saving the title

        // Look at how easy it is to manipulate the stream of data.
        // You can keep adding more and more ingredients to your recipe and not mess anything up.

        // RxJava comes with a ton of operators. (Refer https://github.com/ReactiveX/RxJava/wiki/Alphabetical-List-of-Observable-Operators)
        // It is intimidating how many operators there are, but it's worth reviewing so you know what's available.
        // It will take a while to internalize the operators but you'll have true power at your fingertips once you do.

        // Key idea #3: Operators let you do anything to the stream of data.
        // You can setup complex logic using nothing but chains of simple operators.
        // It breaks down your code into composable bits and pieces.
        // That's functional reactive programming. The more you use it, the more it changes the way you think about programs.

        // If you want to implement your own operators, check out this wiki page (https://github.com/ReactiveX/RxJava/wiki/Implementing-Your-Own-Operators)
    } //End-of-psvm
}
