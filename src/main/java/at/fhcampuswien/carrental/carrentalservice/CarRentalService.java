package at.fhcampuswien.carrental.carrentalservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CarRentalService {

    private List<Car> cars;
    private List<Rental> rentals;

    public CarRentalService() {
        cars = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public void rentCar(Rental rental) {
        Car car = rental.getCar();
        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available for rental");
        }
        car.setAvailable(false);
        rentals.add(rental);
    }

    public List<Rental> getAllRentals() {
        return rentals;
    }
}

