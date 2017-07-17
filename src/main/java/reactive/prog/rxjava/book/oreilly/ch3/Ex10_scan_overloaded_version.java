package reactive.prog.rxjava.book.oreilly.ch3;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;

import java.math.BigInteger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

// scan((total,increment) -> {..}) takes two parameters:
// the last generated value (known as the accumulator) and current value from upstream Observable.
// In the first iteration, total is simply the first item from progress,
// whereas in the second iteration it becomes the result of scan() from the previous one.
// Eg. Use case: progress of data transfer. scan() is like a bulldozer,
// going through the source (upstream) Observable and accumulating items.
// Overloaded version of scan() can provide an initial value (if it is different than simply the first element):
// scan(initValue, (total,increment) -> {..})
public class Ex10_scan_overloaded_version {
    public static void main(String[] args) {
        Observable<Integer> factorials =
                Observable.range(2, 10)
                .scan(1, (acc, inc) -> acc * inc);

        factorials.forEach(System.out::println);
    }
}
