package blog.danlew.net;

import rx.Observable;
import rx.functions.Func1;

// Ref.: http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
// Adding Transformation capability to Lesson4
public class Lesson5 {
    public static void main(String[] args) {

        // Suppose I want to append my signature to the "Hello, world!" output.
        // Option 1: Mutating the Observable
        // This works if you have control over your Observable.
        Observable.just("Hello World!" +  " - Karthik")
                .subscribe(resultString -> System.out.println(resultString));

        // What if you're using someone else's library? I'll not have control of the Observable.
        // What if I use my Observable in multiple places but only sometimes want to add the signature?
        // Option 2: Mutating the Subscriber
        Observable.just("Hello World!")
                .subscribe(resultString -> System.out.println(resultString +  " - Karthik"));

        // Option 2 is unsatisfactory as well.
        // Subscribers to be as lightweight as possible because I might be running them on the main thread.
        // And on a more conceptual level, Subscribers are supposed to be the thing that reacts, not the thing that mutates.
        // Option 3: Introducing Transformation Operators as intermediary step to the rescue
        Observable.just("Hello World!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(final String resultString) {
                        return resultString +  " - Karthik";
                    }
                })
                .subscribe(resultString -> System.out.println(resultString));

        // Further simplifying Option 3 using Lambdas
        Observable.just("Hello World!")
                .map(str -> str +  " - Karthik")
                .subscribe(resultString -> System.out.println(resultString));

        // Lesson:
        // Our map() operator is basically an Observable that transforms an item.
        // We can chain as many map() calls as we want together, polishing the data into
        // a perfect, consumable form for our end Subscriber

        // Key Takeaways:
        // Key idea #1: Observable and Subscriber can do anything.
        // It's a general framework that can handle just about any problem.
        // Your Observable could be a database query, the Subscriber taking the results and displaying them on the screen.
        // Your Observable could be a click on the screen, the Subscriber reacting to it.
        // Your Observable could be a stream of bytes read from the internet, the Subscriber could write it to the disk.
        // Key idea #2: The Observable and Subscriber are independent of the transformational steps in between them.
        // I can stick as many map() calls as I want in between the original source Observable and its ultimate Subscriber.
        // The system is highly composable: it is easy to manipulate the data.
        // As long as the operators work with the correct input/output data I could make a chain that goes on forever ;)
    } //End-of-psvm
}
