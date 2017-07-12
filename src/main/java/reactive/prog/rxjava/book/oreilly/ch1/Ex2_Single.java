package reactive.prog.rxjava.book.oreilly.ch1;

import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;

//Understanding rx.Single
public class Ex2_Single {
    public static Single<String> getDataA() {
        return Single.<String> create(o -> {
            o.onSuccess("A");
        }).subscribeOn(Schedulers.io());
    }

    public static Single<String> getDataB() {
        return Single.just("B")
                .subscribeOn(Schedulers.io());
    }

    public static void main(String[] args) {
        // merge a & b into an Observable stream of 2 values
        // Note how two Singles are merged into an Observable.
        // This could result in an emission of [A, B] or [B, A], or [A], or [B] depending on which completes first.
        // We can now use Single instead of Observable to represent the data fetches,
        // but merge them into a stream of values:
        Observable<String> o2 = Single.merge(getDataA(), getDataB());
        o2.subscribe(s -> print(s, "1"));
        System.out.println("");
        o2.subscribe(s -> print(s, "2"));
        System.out.println("");
        o2.subscribe(s -> print(s, "3"));
        System.out.println("");
        o2.subscribe(s -> print(s, "4"));
        System.out.println("");
        o2.subscribe(s -> print(s, "5"));
        System.out.println("");
        o2.subscribe(s -> print(s, "6"));
        System.out.println("");
        o2.subscribe(s -> print(s, "7"));
    }

    private static void print(String sub, String extra) {
        extra = " " + extra + " ";
        System.out.println(extra+sub+extra);
    }
}
