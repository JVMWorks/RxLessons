package reactive.prog.rxjava.book.oreilly.ch3;

import rx.Observable;

import java.util.concurrent.TimeUnit;

import static rx.Observable.timer;
import static java.util.concurrent.TimeUnit.SECONDS;

//Understanding delay() and timer() operators
public class Ex5_delay_timer {
    public static void main(String[] args) {
        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .delay(word -> timer(word.length(), SECONDS))
                .subscribe(System.out::println);

        sleep(15);
        System.out.println("----------------");

        // Remember that delay() can be rewritten to timer() plus flatMap()?
        // Can you try that?
        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .flatMap(word -> timer(word.length(), TimeUnit.SECONDS).map(i -> word) )
                .subscribe(System.out::println);
        sleep(15);
        System.out.println("----------------");
    }

    // When running this program, even after subscribing,
    // your application will terminate immediately WITHOUT displaying any results
    // because emission occurs in the background.
    // For this reason, this program is run WITH this sleep(), for us to see the event emission.
    private static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
