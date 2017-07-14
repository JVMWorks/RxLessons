# RxLessons - Demo Project to Learn RxJava 1

## Code References for RxJava 1
* Lesson[1-5] classes from Blog post 1
* Lesson[6-7] classes from Blog post 2
* Lesson[8-10] classes from Blog post 3


## Code References for RxJava 2

## References
* [Grokking RxJava, Part 1: The Basics](http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/)
* [Grokking RxJava, Part 2: Operator, Operator](http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/)
* [Grokking RxJava, Part 3: Reactive with Benefits](http://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/)
* [RxJava Wiki - Home](https://github.com/ReactiveX/RxJava/wiki)
* [Alphabetical List of Operators](http://reactivex.io/documentation/operators.html#alphabetical)
* [Error handling in RxJava](http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/)
* [RxJava Docs - Error Handling](https://github.com/ReactiveX/RxJava/wiki/Error-Handling)
* [Grokking RxJava, Part 4: Reactive Android](http://blog.danlew.net/2014/10/08/grokking-rxjava-part-4/)
* [The introduction to Reactive Programming you've been missing](https://gist.github.com/staltz/868e7e9bc2a7b8c1f754#the-introduction-to-reactive-programming-youve-been-missing)
* [RxJava magic (finally) goes away](http://konmik.com/post/rxjava_magic_finally_goes_away/)
* [How to think about Subjects in RxJava (Part 1)](https://tech.instacart.com/how-to-think-about-subjects-in-rxjava-part-1-ca509b981020)
* [Learning RxJava for Android by example](https://github.com/kaushikgopal/RxJava-Android-Samples)
* [Building Yahnac’s Rx Pipeline](http://www.malmstein.com/blog/2015/04/05/yahnacs-rx-pipeline/)
* [The Mayans Lost Guide to RxJava on Android by Fernando Cejas @ SpeakerDeck](https://speakerdeck.com/android10/the-mayans-lost-guide-to-rxjava-on-android)
* [Subscribe It While It's Hot: Cached Rest Requests With RxJava](http://fedepaol.github.io/blog/2016/01/01/cached-rest-requests-with-rxjava/?utm_source=androiddevdigest)
* [RxJava 2 Android Examples - Migration From RxJava 1 to RxJava 2 - How to use RxJava 2 in Android](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples)
* [Quora - What is the best way to learn RxJava for Android?](https://www.quora.com/What-is-the-best-way-to-learn-RxJava-for-Android)
* [Book] Reactive Programming with RxJava
* [Book][Code - Reactive Programming with RxJava](https://github.com/nurkiewicz/rxjava-book-examples)


## Rx Concepts

The basic building blocks of reactive code are Observables and Subscribers. 
An Observable emits items; a Subscriber consumes those items.
The smallest building block is actually an Observer, but in practice you are most often using Subscriber because that's how you hook up to Observables
 
An Observable may emit any number of items (including zero items), then it terminates either by successfully completing, or due to an error.
For each Subscriber it has, an Observable calls Subscriber.onNext() any number of times, followed by either Subscriber.onComplete() or Subscriber.onError().

This differs from Observer Pattern in that this doesn't start emitting items until someone explicitly subscribes to them
The term used is "hot" vs. "cold" Observables.
A HOT Observable emits items all the time, even when no one is listening.
A COLD Observable only emits items when it has a subscriber (and is what I'm using in all my examples).
This distinction isn't that important to initially learning RxJava.
 
Our map() operator is basically an Observable that transforms an item.
We can chain as many map() calls as we want together, polishing the data into a perfect, consumable form for our end Subscriber

** Understanding Observables by Example **
* Observable<Tweet> tweets : where tweets represent stream of events.
* Observable<Customer> customers : where customers represent list of customers, probably from a db query.
* Observable<HttpResponse> response : where response yields just one event (value) until it terminates.
* Observable<Void> completionCallback : where completionCallback represents completion of a possible transaction. 
 
**Key idea #1: Observable and Subscriber can do anything.**
It's a general framework that can handle just about any problem.
Your Observable could be a database query, the Subscriber taking the results and displaying them on the screen.
Your Observable could be a click on the screen, the Subscriber reacting to it.
Your Observable could be a stream of bytes read from the internet, the Subscriber could write it to the disk.

**Key idea #2: The Observable and Subscriber are independent of the transformational steps in between them.**
I can stick as many map() calls as I want in between the original source Observable and its ultimate Subscriber.
The system is highly composable: it is easy to manipulate the data.
As long as the operators work with the correct input/output data I could make a chain that goes on forever ;)

**Key idea #3: Operators let you do anything to the stream of data.**
You can setup complex logic using nothing but chains of simple operators.
It breaks down your code into composable bits and pieces.
That's functional reactive programming. The more you use it, the more it changes the way you think about programs.

You can compose/combine many API calls using the Transformation Operators of Rx.
Imagine how you'd go about doing it the Async Callbacks way. You know how much of a pain in the ass it is to keep all your API calls synced, having to link their callbacks together before presenting the data?
Look at how easy it is to manipulate the stream of data.
You can keep adding more and more ingredients to your recipe and not mess anything up.

**ErrorHandling:** They mark when an Observable is going to stop emitting items and the reason for why (either a successful completion, or an unrecoverable error).
Every Observable ends with either a single call to onCompleted() or onError().
There's a few takeaways from this pattern:
* **onError() is called if an Exception is thrown at any time.** This makes error handling much simpler. I can just handle every error at the end in a single function.
* **The operators don't have to handle the Exception.** You can leave it up to the Subscriber to determine how to handle issues with any part of the Observable chain because Exceptions skip ahead to onError().
* **You know when the Subscriber has finished receiving items.** Knowing when a task is done helps the flow of your code. (Though it is possible that an Observable may never complete.)

This pattern a lot easier than traditional error handling. 
With callbacks, you have to handle errors in each callback. 
Not only does that lead to repetitious code, but it also means that each callback must know how to handle errors, 
meaning your callback code is tightly coupled to the caller

With RxJava's pattern, your Observable doesn't even have to know what to do with errors! 
Nor do any of your operators have to handle error states - they'll be skipped in cases of critical failure. 
You can leave all your error handling to the `Subscriber`.

## Schedulers

You've got an Android app that makes a network request. 
That could take a long time, so you load it in another thread. Suddenly, you've got problems!

Multi-threaded Android applications are difficult because you have to make sure to run the right code on the right thread; 
mess up and your app can crash. The classic exception occurs when you try to modify a View off of the main thread.

In RxJava, you can tell your `Observer` code which thread to run on using `subscribeOn()`, 
and which thread your `Subscriber` should run on using `observeOn()`:
```
myObservableServices.retrieveImage(url) // Observer code
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(bitmap -> myImageView.setImageBitmap(bitmap));     // Subscriber code
```

How simple is that? Everything that runs before my Subscriber runs on an I/O thread. 
Then in the end, my View manipulation happens on the main thread. 
This is one reason why I try to keep my Subscriber as lightweight as possible; 
I want to minimize how much I block the main thread.

The great part about this is that I can attach `subscribeOn()` and `observeOn()` to any `Observable`! 
They're just operators! I don't have to worry about what the `Observable` or its previous operators are doing;
I can just stick this at the end for easy threading. 
Deferring calls to `observeOn()` and `subscribeOn()` is good practice because it gives the `Subscriber` more flexibility to handle processing as it wants. 
For instance, an `Observable` might take a while, but if the `Subscriber` is already in an I/O thread you wouldn't need to observe it on a new thread.
[Scratching my head on this at the moment thinking of a use-case where this happens..TODO]

With an AsyncTask or the like, I have to design my code around which parts of the code I want to run concurrently. 
With RxJava, my code stays the same - it's just got a touch of concurrency added on.

## Subscriptions

When you call `Observable.subscribe()`, it returns a `Subscription`. 
This represents the link between your `Observable` and your `Subscriber`.
```
Subscription subscription = Observable.just("Hello, World!")
    .subscribe(s -> System.out.println(s));
```    

You can use this `Subscription` to sever the link later on:
```
subscription.unsubscribe();
System.out.println("Unsubscribed=" + subscription.isUnsubscribed());
// Outputs "Unsubscribed=true"
```

What's nice about how RxJava handles unsubscribing is that it stops the chain. 
If you've got a complex chain of operators, using unsubscribe will terminate wherever it is currently executing code. 
No unnecessary work needs to be done!

## Frequently User Transformation Operators

* `map`
* `flatMap`
* `filter()` emits the same item it received, but only if it passes the boolean check
* `take(number)` emits, at most, the number of items specified. (If there are fewer than said #, it'll just stop early.)
* `doOnNext()` allows us to add extra behavior each time an item is emitted, in this case saving the title.

RxJava comes with [a ton of operators](https://github.com/ReactiveX/RxJava/wiki/Alphabetical-List-of-Observable-Operators).
It is intimidating how many operators there are, but it's worth reviewing so you know what's available.
It will take a while to internalize the operators but you'll have true power at your fingertips once you do.

If you want to implement your own operators, check out [this wiki page](https://github.com/ReactiveX/RxJava/wiki/Implementing-Your-Own-Operators).