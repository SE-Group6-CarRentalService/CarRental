package at.fhcampuswien.carrental.carrentalservice.repository;

import at.fhcampuswien.carrental.carrentalservice.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
