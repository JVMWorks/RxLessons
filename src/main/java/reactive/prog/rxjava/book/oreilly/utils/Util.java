package reactive.prog.rxjava.book.oreilly.utils;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Util {
    public static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getNumber(int i) {
        System.out.println("getNumber(): " + LocalTime.now() + ": " + i);
        sleep(i);
        System.out.println("getNumber(): " + LocalTime.now() + ": " + i);
        return i;
    }
}
