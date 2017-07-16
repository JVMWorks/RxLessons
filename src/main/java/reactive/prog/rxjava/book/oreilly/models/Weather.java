package reactive.prog.rxjava.book.oreilly.models;

public class Weather {
    private final Temperature temperature;

    public Weather(Temperature temperature, Wind wind) {
        //...
        this.temperature = temperature;
    }

    public boolean isSunny() {
        return true;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}