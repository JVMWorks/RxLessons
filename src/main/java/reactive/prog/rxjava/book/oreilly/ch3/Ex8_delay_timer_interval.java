package reactive.prog.rxjava.book.oreilly.ch3;

import org.apache.commons.lang3.RandomUtils;
import reactive.prog.rxjava.book.oreilly.utils.Util;
import rx.Observable;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static reactive.prog.rxjava.book.oreilly.utils.Util.getNumber;

public class Ex8_delay_timer_interval {
    public static Observable<Integer> generateAsyncRandomNumbers() {
        return Observable.interval(RandomUtils.nextInt(1,3),TimeUnit.SECONDS)
                .map(i -> RandomUtils.nextInt(1,100))
                .take(3);
    }

    public static void main(String[] args) {
        System.out.println("Demo delay()..It is an instance method of Observable object");
        generateAsyncRandomNumbers()
                .doOnNext(i -> System.out.println(LocalTime.now() + ": " + i))
                .delay(1, TimeUnit.SECONDS)
                .forEach(i -> System.out.println(LocalTime.now() + ": " + i));
        Util.sleep(10);

        System.out.println("Demo timer()..It is a static method of Observable object");
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Util.sleep(2);

        System.out.println("Demo interval()..It is a static method of Observable object");
        Observable.interval(1, TimeUnit.SECONDS).subscribe(System.out::println);
        Util.sleep(5);
    }
}
