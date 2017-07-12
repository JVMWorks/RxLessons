package reactive.prog.rxjava.book.oreilly.ch1;

import rx.Observable;

import java.util.concurrent.CompletableFuture;

public class Ex3_Composition {
    public static void main(String[] args) {

        CompletableFuture<String> f1 = getDataAsFuture(1);
        CompletableFuture<String> f2 = getDataAsFuture(2);

        CompletableFuture<String> f3 = f1.thenCombine(f2, (x, y) -> x+y);

        Observable<String> o1 = getDataAsObservable(1);
        Observable<String> o2 = getDataAsObservable(2);

        // A multivalued Observable type is also useful when composing single-valued responses, such as from Futures.
        // However, it means waiting until all Futures are completed before emitting anything.
        Observable<String> o3 = Observable.zip(o1, o2, (x, y) -> x+y);

        // Oftentimes, it is preferable to emit each returned Future value as it completes.
        // In this case, Observable.merge (or the related flatMap) is preferable.
        // It allows composing the results (even if each is just an Observable emitting one value)
        // into a stream of values that are each emitted as soon as they are ready:
        // o4 is now a stream of o1 and o2 that emits each item without waiting
        Observable<String> o4 = Observable.merge(o1, o2);
    }

    private static CompletableFuture<String> getDataAsFuture(int i) {
        return CompletableFuture.completedFuture("Done: " + i + "\n");
    }

    private static Observable<String> getDataAsObservable(int i) {
        return Observable.just("Done: " + i + "\n");
    }

}
