package at.fhcampuswien.carrental.carrentalservice.controller;

import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.model.Car;
import at.fhcampuswien.carrental.carrentalservice.model.Rental;
import at.fhcampuswien.carrental.carrentalservice.repository.CarRepository;
import at.fhcampuswien.carrental.carrentalservice.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    // Get all rentals
    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Create a new rental
    @PostMapping("/rentals")
    public Rental createRental(@Valid @RequestBody Rental rental) {
        Car car = carRepository.findById(rental.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car", "id", rental.getCar().getId()));
        car.setAvailable(false);
        carRepository.save(car);
        return rentalRepository.save(rental);
    }

    // Get a single rental
    @GetMapping("/rentals/{id}")
    public Rental getRentalById(@PathVariable(value = "id") Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));
    }

    // Update a rental
    @PutMapping("/rentals/{id}")
    public Rental updateRental(@PathVariable(value = "id") Long rentalId,
                               @Valid @RequestBody Rental rentalDetails) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));

        rental.setStartDate(rentalDetails.getStartDate());
        rental.setEndDate(rentalDetails.getEndDate());
        rental.setCustomerName(rentalDetails.getCustomerName());
        rental.setCustomerEmail(rentalDetails.getCustomerEmail());
        rental.setCustomerPhone(rentalDetails.getCustomerPhone());
        rental.setCar(rentalDetails.getCar());

        return rentalRepository.save(rental);
    }

    // Delete a rental
    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable(value = "id") Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));

        Car car = carRepository.findById(rental.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car", "id", rental.getCar().getId()));
        car.setAvailable(true);
        carRepository.save(car);

        rentalRepository.delete(rental);

        return ResponseEntity.ok().build();
    }
}
