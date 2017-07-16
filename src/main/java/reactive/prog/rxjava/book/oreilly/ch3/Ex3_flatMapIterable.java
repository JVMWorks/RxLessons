package reactive.prog.rxjava.book.oreilly.ch3;

import reactive.prog.rxjava.book.oreilly.models.Customer;
import reactive.prog.rxjava.book.oreilly.models.Order;
import rx.Observable;

// Now imagine that you would like to use a method returning an Iterable (like List or Set).
// For example, if Customer has a simple List<Order> getOrders(),
// you are forced to go through several operators to take advantage of it in Observable pipeline
public class Ex3_flatMapIterable {
    public static void main(String[] args) {
        Observable<Customer> customers = Observable.just(new Customer(), new Customer());

        // You need a one-to-many transformation, a single event is expanded into multiple sub-events.
        // For example, a stream of customers is translated into streams of their orders,
        // for which each customer can have an arbitrary number of orders.
        Observable<Order> orders = customers.flatMap(
                customer -> Observable.from(customer.getOrders())
        );

        // Alternative form : slightly verbose
        Observable<Order> orders2 = customers
                .map(Customer::getOrders)
                .flatMap(Observable::from);

        // This situation is so common, that Rx has an operator for this - flatMapIteratable()
        Observable<Order> orders3 = customers.flatMapIterable(Customer::getOrders);

        // Note:
        // You must take care when simply wrapping methods in an Observable.
        // If getOrders() was not a simple getter but an expensive operation in terms of run time,
        // it is better to implement getOrders() to explicitly return Observable<Order>.
    }
}
