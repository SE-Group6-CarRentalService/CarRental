package at.fhcampuswien.carrental.carrentalservice.service;

import at.fhcampuswien.carrental.carrentalservice.dto.RentalDto;
import at.fhcampuswien.carrental.carrentalservice.model.Car;
import at.fhcampuswien.carrental.carrentalservice.model.Rental;
import at.fhcampuswien.carrental.carrentalservice.entity.User;
import at.fhcampuswien.carrental.carrentalservice.exception.BadRequestException;
import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.mapper.RentalMapper;
import at.fhcampuswien.carrental.carrentalservice.repository.RentalRepository;
import at.fhcampuswien.carrental.carrentalservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private CarRepository carRepository;
    private UserRepository userRepository;
    private CurrencyConversionService currencyConversionService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, CarRepository carRepository, UserRepository userRepository, CurrencyConversionService currencyConversionService) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RentalDto getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + id));
        return convertToDto(rental);
    }

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        Car car = carRepository.findById(rentalDto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + rentalDto.getCarId()));
        User user = userRepository.findById(rentalDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + rentalDto.getUserId()));

        // Check if the car is available for the given time period
        if (!isCarAvailable(car, rentalDto.getStartDate(), rentalDto.getEndDate())) {
            throw new BadRequestException("Car is not available for the given time period");
        }

        // Calculate rental price in default currency
        double price = calculateRentalPrice(car.getPricePerDay(), rentalDto.getStartDate(), rentalDto.getEndDate());

        // Convert price to user's preferred currency
        double convertedPrice = currencyConversionService.convert(price, user.getPreferredCurrency());

        // Create rental entity and save to database
        Rental rental = new Rental(car, user, rentalDto.getStartDate(), rentalDto.getEndDate(), convertedPrice);
        rentalRepository.save(rental);

        return convertToDto(rental);
    }

    @Override
    public void deleteRentalById(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rental not found with id " + id);
        }
        rentalRepository.deleteById(id);
    }

    private RentalDto convertToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setCarId(rental.getCar().getId());
        rentalDto.setUserId(rental.getUser().getId());
        rentalDto.setStartDate(rental.getStartDate());
        rentalDto.setEndDate(rental.getEndDate());
        rentalDto.setPrice(rental.getPrice());
        return rentalDto;
    }

    private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
        List<Rental> rentals = rentalRepository.findByCarAndDates(car, startDate, endDate);
        return rentals.isEmpty();
    }

    private double calculateRentalPrice(double pricePerDay, LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return days * pricePerDay;
    }

}
