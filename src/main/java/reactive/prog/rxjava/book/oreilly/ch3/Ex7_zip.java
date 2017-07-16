package reactive.prog.rxjava.book.oreilly.ch3;

import reactive.prog.rxjava.book.oreilly.models.City;
import reactive.prog.rxjava.book.oreilly.models.Vacation;
import reactive.prog.rxjava.book.oreilly.models.Weather;
import rx.Observable;

import java.time.LocalDate;

// Zipping is the act of taking two (or more) streams and combining them with each other in such a way that
// each element from one stream is paired with corresponding event from the other.
// A downstream event is produced by composing the first event from each, second event from each stream, and so on.
// Therefore, events appear only when all upstream sources emit an event.
// zip() can take up to nine upstream Observables and emit an event only when all of them emit an event.
public class Ex7_zip {
    public static void main(String[] args) {
        Observable<LocalDate> nextTenDays = Observable
                .range(1, 10)
                .map(i -> LocalDate.now().plusDays(i));

        Observable<City> cities = Observable.just(City.Delhi, City.Mumbai, City.Kolkota, City.Chennai);

        Observable<Vacation> vacations = nextTenDays.flatMap(date -> cities.map(city -> new Vacation(city, date)));

        Observable<Vacation> possibleVacations = vacations.flatMap(v ->
                Observable.zip(
                        v.weather().filter(Weather::isSunny),
                        v.cheapFlightFrom(City.Bengaluru),
                        v.cheapHotel(),
                        (w,f,h) -> v  //If only the above 3 values are got, this lambda is executed
                ));

        possibleVacations.subscribe(System.out::println);
        System.out.println("---------------");
    }
}
