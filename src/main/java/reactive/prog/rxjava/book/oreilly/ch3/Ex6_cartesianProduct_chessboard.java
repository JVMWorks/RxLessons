package reactive.prog.rxjava.book.oreilly.ch3;

import rx.Observable;

// We will need to produce a Cartesian product of all values from two streams.
// For example we might have two Observables, one with chessboard’s rows (ranks, 1 to 8)
// and one with columns (files, a to h). We would like to find all possible 64 squares on a chessboard:
public class Ex6_cartesianProduct_chessboard {
    public static void main(String[] args) {
        Observable<String> rows = Observable.range(1, 8).map(i -> Integer.toString(i));
        Observable<String> columns = Observable.just("a", "b", "c", "d", "e", "f", "g", "h");
        Observable<String> result = rows.flatMap(row -> columns.map(coln -> row + coln));
        result.subscribe(s -> System.out.print(s + "\t"));
        System.out.println("\n");
        System.out.println("---------------");
    }
}
