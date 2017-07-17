package reactive.prog.rxjava.book.oreilly.ch3;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

// scan((total,increment) -> {..}) takes two parameters:
// the last generated value (known as the accumulator) and current value from upstream Observable.
// In the first iteration, total is simply the first item from progress,
// whereas in the second iteration it becomes the result of scan() from the previous one.
// Eg. Use case: progress of data transfer. scan() is like a bulldozer,
// going through the source (upstream) Observable and accumulating items.
public class Ex9_scan {
    private static Observable<Long> transferFile() {
        return Observable
                .interval(500, MILLISECONDS)
                .map(x -> RandomUtils.nextLong(10, 30))
                .take(10);
    }

    private static Observable<Long> transferFile2() {
        return Observable
                .interval(1, SECONDS)
                .map(x -> 10L)
                .take(10);
    }

    public static void main(String[] args) {
        Observable<Long> progress = transferFile();

        Observable<Long> totalProgress = progress
                .scan((total, chunk) -> total + chunk);

        totalProgress
                .toBlocking()
                .subscribe(System.out::println);

        System.out.println("Showing progress in percentages:");
        transferFile2()
                .scan((total,chunk) -> total+chunk)
                .map(x -> x+"%")
                .toBlocking()
                .subscribe(System.out::println);
    }
}
