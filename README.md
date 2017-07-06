# Demo Project to Learn RxJava 1 and 2

## Code References for RxJava 1
* Lesson[1-5] classes

## Code References for RxJava 2

## References
* [Grokking RxJava, Part 1: The Basics](http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/)

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
