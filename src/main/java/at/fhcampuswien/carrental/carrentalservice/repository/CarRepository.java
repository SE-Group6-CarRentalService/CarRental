package at.fhcampuswien.carrental.carrentalservice.repository;

import at.fhcampuswien.carrental.carrentalservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Add any custom query methods here
}
