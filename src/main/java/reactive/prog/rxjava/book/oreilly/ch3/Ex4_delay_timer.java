package reactive.prog.rxjava.book.oreilly.ch3;

import rx.Observable;

import java.util.concurrent.TimeUnit;

import static rx.Observable.just;

//Understanding delay() and timer() operators
public class Ex4_delay_timer {
    public static void main(String[] args) {

        // delay() basically takes an upstream Observable and shifts all events further in time.
        // code below will not emit 1, 2 and 3 immediately upon subscription but after given delay.
        just(1, 2, 3)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        sleep();
        System.out.println("-----");

        // We generate an artificial event from timer() that we completely ignore.
        // However, using flatMap() we replace that artificial event (zero, in i value) with
        // three immediately emitted values: 4,5,6.
        Observable
                .timer(1, TimeUnit.SECONDS)
                .flatMap(i -> Observable.just(4,5,6))
                .subscribe(System.out::println);
        sleep();
        System.out.println("-----");

        // Lesson: How is delay() different from timer()
        // delay() is more comprehensive than timer()
        // because it shifts every single event further by a given amount of time,
        // whereas timer() simply “sleeps” and emits a special event after given time.
    }

    // When running this program, even after subscribing,
    // your application will terminate immediately WITHOUT displaying any results
    // because emission occurs in the background.
    // For this reason, this program is run WITH this sleep(), for us to see the event emission.
    private static void sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
