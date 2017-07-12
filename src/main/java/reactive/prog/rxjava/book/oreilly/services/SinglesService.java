package reactive.prog.rxjava.book.oreilly.services;

import rx.Single;
import rx.schedulers.Schedulers;

public class SinglesService {
    public static Single<String> getDataA() {
        return Single.<String> create(o -> {
            o.onSuccess("DataA");
        }).subscribeOn(Schedulers.io());
    }

    public static Single<String> getDataB() {
        return Single.just("DataB")
                .subscribeOn(Schedulers.io());
    }
}
