package at.fhcampuswien.carrental.carrentalservice.mapper;

import at.fhcampuswien.carrental.carrentalservice.dto.RentalDto;
import at.fhcampuswien.carrental.carrentalservice.model.Rental;
import org.springframework.stereotype.Component;

package at.fhcampuswien.carrental.carrentalservice.service.mapper;

import at.fhcampuswien.carrental.carrentalservice.dto.RentalDto;
import at.fhcampuswien.carrental.carrentalservice.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public Rental toEntity(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setId(rentalDto.getId());
        rental.setCarId(rentalDto.getCarId());
        rental.setUserId(rentalDto.getUserId());
        rental.setStartDate(rentalDto.getStartDate());
        rental.setEndDate(rentalDto.getEndDate());
        rental.setTotalPrice(rentalDto.getTotalPrice());
        rental.setCurrency(rentalDto.getCurrency());
        return rental;
    }

    public RentalDto toDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setCarId(rental.getCarId());
        rentalDto.setUserId(rental.getUserId());
        rentalDto.setStartDate(rental.getStartDate());
        rentalDto.setEndDate(rental.getEndDate());
        rentalDto.setTotalPrice(rental.getTotalPrice());
        rentalDto.setCurrency(rental.getCurrency());
        return rentalDto;
    }
}


