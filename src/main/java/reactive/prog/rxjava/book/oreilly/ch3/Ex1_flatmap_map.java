package reactive.prog.rxjava.book.oreilly.ch3;


import rx.Observable;

public class Ex1_flatmap_map {
    public static void main(String[] args) {
        Observable<Integer> numbers = Observable.just(2,5, 10);
        numbers.doOnNext(i -> System.out.println("just: " + i))
        .subscribe();

        System.out.println();
        numbers.map(x -> x * 2)
                .doOnNext(i -> System.out.println("map: " + i))
                .subscribe();

        System.out.println();
        numbers.filter(x -> x != 10)
                .doOnNext(i -> System.out.println("filter: " + i))
                .subscribe();

        System.out.println();
        numbers.flatMap(x -> Observable.just(x*2))
                .doOnNext(i -> System.out.println("flatMap: " + i))
                .subscribe();

        System.out.println();
        numbers.flatMap(x -> (x != 10) ? Observable.just(x) : Observable.empty())
                .doOnNext(i -> System.out.println("filter using flatMap: " + i))
                .subscribe();

        // Lesson: As a rule of thumb, you use flatMap() for the following situations:
        // 1. The result of transformation in map() must be an Observable.
        //    For example, performing long-running, asynchronous operation on each element of the stream without blocking.
        // 2. You need a one-to-many transformation, a single event is expanded into multiple sub-events.
        //    For example, a stream of customers is translated into streams of their orders,
        //    for which each customer can have an arbitrary number of orders.
    }
}
