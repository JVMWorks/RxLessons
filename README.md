# Demo Project to Learn RxJava 1 and 2

## Code References for RxJava 1
* Lesson[1-5] classes

## Code References for RxJava 2

## References
* [Grokking RxJava, Part 1: The Basics](http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/)
* [Grokking RxJava, Part 2: Operator, Operator](http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/)

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