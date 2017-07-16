package reactive.prog.rxjava.book.oreilly.ch3;

import reactive.prog.rxjava.book.oreilly.models.CarPhoto;
import reactive.prog.rxjava.book.oreilly.models.LicensePlate;
import rx.Observable;

// Situation
// Imagine that you receive a stream of photographs of cars entering a highway.
// For each car, we can run a rather expensive optical character recognition algorithm that returns
// the registration number from the license plate of the cars.
// Obviously, the recognition can fail, in which case this algorithm returns nothing.
// It can also fail with an exception, or
// for some bizarre reason it can return two license plates for a single car.
public class Ex2_Cars_flatmap {

    //stream of photographs of cars entering a highway.
    public static Observable<CarPhoto> cars() {
        return Observable.just(new CarPhoto());
    }

    //the registration number from the license plate of the cars.
    public static Observable<LicensePlate> recognize(CarPhoto photo) {
        return Observable.just(new LicensePlate());
    }

    public static void main(String[] args) {
        //stream of photographs of cars entering a highway.
        Observable<CarPhoto> cars = cars();

        //the registration number from the license plate of the cars.
        //Cumbersome form..not recommended because of NESTED OBSERVABLES
        Observable<Observable<LicensePlate>> plates = cars.map(Ex2_Cars_flatmap::recognize);

        //the registration number from the license plate of the cars.
        //Recommended to use flatmap here..ease of use to stream observable events
        Observable<LicensePlate> plates2 = cars.flatMap(Ex2_Cars_flatmap::recognize);
    }
}
