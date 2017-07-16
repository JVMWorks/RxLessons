package reactive.prog.rxjava.book.oreilly.ch3;

import reactive.prog.rxjava.book.oreilly.utils.Util;
import rx.Observable;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static reactive.prog.rxjava.book.oreilly.utils.Util.getNumber;

public class Ex8_delay_timer_interval {
    public static void main(String[] args) {
        System.out.println("Demo delay()..It is an instance method of Observable object");
        Observable.just(getNumber(1),getNumber(2), getNumber(3))
                .doOnNext(i -> System.out.println(LocalTime.now() + ": " + i))
                .delay(1, TimeUnit.SECONDS)
                .forEach(i -> System.out.println(LocalTime.now() + ": " + i));
        Util.sleep(5);

        System.out.println("Demo timer()..It is a static method of Observable object");
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Util.sleep(2);

        System.out.println("Demo interval()..It is a static method of Observable object");
        Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);
        Util.sleep(5);
    }
}
