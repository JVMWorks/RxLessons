package blog.danlew.net;

import rx.Observable;
import rx.functions.Action1;

// Ref.: http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
// Simpler version of Lesson2 by getting rid of variables
public class Lesson3 {

    public static void main(String[] args) {

        // Strictly speaking, Observable.just() isn't exactly the same as our initial code.
        // Why? WE'll get to it later. See lesson N.
        Observable.just("Hello World!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        // All this does is print each String emitted by the Observable.
                        System.out.println(s);
                    }
                });
    }
}
