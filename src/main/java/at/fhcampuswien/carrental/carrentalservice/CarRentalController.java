package at.fhcampuswien.carrental.carrentalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarRentalController {

    @Autowired
    private CarRentalService carRentalService;

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        carRentalService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carRentalService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car car = carRentalService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/rentals")
    public ResponseEntity<?> rentCar(@RequestBody Rental rental) {
        try {
            carRentalService.rentCar(rental);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = carRentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
}

