package blog.danlew.net;

import rx.Observable;

// Ref.: http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
// Simpler version of Lesson3 by making use of Lambdas
public class Lesson4 {
    public static void main(String[] args) {
        Observable.just("Hello World!")
                .subscribe(resultString -> System.out.println(resultString));
    }
}
