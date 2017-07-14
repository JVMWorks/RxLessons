package reactive.prog.rxjava.book.oreilly.models;

public class Tweet {
    private int id;

    public Tweet(int id) {
        this.id = id;
    }

    public String toString() {
        return "Tweet:"+id;
    }
}
