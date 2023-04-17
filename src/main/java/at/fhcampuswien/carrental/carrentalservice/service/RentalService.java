package at.fhcampuswien.carrental.carrentalservice.service;

import java.util.List;

import at.fhcampuswien.carrental.carrentalservice.dto.RentalDto;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);
    List<RentalDto> getAllRentals();
    RentalDto getRentalById(Long id);
    RentalDto updateRental(Long id, RentalDto rentalDto);
    void deleteRental(Long id);
}
