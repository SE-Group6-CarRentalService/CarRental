package at.fhcampuswien.carrental.carrentalservice.service;

import at.fhcampuswien.carrental.carrentalservice.dto.CarDto;
import at.fhcampuswien.carrental.carrentalservice.model.Car;
import at.fhcampuswien.carrental.carrentalservice.exception.BadRequestException;
import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.mapper.CarMapper;
import at.fhcampuswien.carrental.carrentalservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new ResourceNotFoundException("Car not found with id: " + id);
        }
        return carMapper.toDto(car.get());
    }

    @Override
    public CarDto addCar(CarDto carDto) {
        if (carDto.getId() != null) {
            throw new BadRequestException("Cannot add car with existing ID: " + carDto.getId());
        }
        Car car = carMapper.toEntity(carDto);
        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public CarDto updateCar(CarDto carDto) {
        if (carDto.getId() == null) {
            throw new BadRequestException("Cannot update car without ID");
        }
        Optional<Car> optionalCar = carRepository.findById(carDto.getId());
        if (optionalCar.isEmpty()) {
            throw new ResourceNotFoundException("Car not found with id: " + carDto.getId());
        }
        Car car = optionalCar.get();
        car = carMapper.updateFromDto(carDto, car);
        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public void deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            throw new ResourceNotFoundException("Car not found with id: " + id);
        }
        carRepository.delete(car.get());
    }
}
