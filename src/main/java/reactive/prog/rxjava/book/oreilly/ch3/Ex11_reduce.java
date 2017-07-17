package reactive.prog.rxjava.book.oreilly.ch3;

import reactive.prog.rxjava.book.oreilly.models.CashTransfer;
import rx.Observable;

import java.math.BigDecimal;

// reduce() is basically scan(),
// just that it is not interested in the intermediate results and
// cares to emit only the last event
public class Ex11_reduce {
    private static Observable<CashTransfer> getAllCashTransfers() {
        return Observable.just(new CashTransfer());
    }

    public static void main(String[] args) {
        Observable<BigDecimal> total1 = getAllCashTransfers()
                .reduce(BigDecimal.ZERO, (sum, transaction) -> sum.add(transaction.getAmount())); //Reduce in action

        //Alternative readable way is with multiple compositions
        Observable<BigDecimal> total2 = getAllCashTransfers()
                .map(CashTransfer::getAmount) //Transforming CashTransformer to BigDecimal value that we need for computation
                .reduce(BigDecimal.ZERO, (sum, trans) -> sum.add(trans)); //Reduce in action

        // Lesson:
        // reduce() simply scans through the Observable and discards all but the last item
    }
}
