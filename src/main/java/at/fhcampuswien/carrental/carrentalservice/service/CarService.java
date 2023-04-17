package at.fhcampuswien.carrental.carrentalservice.service;

import at.fhcampuswien.carrental.carrentalservice.dto.CarDto;
import at.fhcampuswien.carrental.carrentalservice.entity.Car;
import at.fhcampuswien.carrental.carrentalservice.exception.BadRequestException;
import at.fhcampuswien.carrental.carrentalservice.exception.ResourceNotFoundException;
import at.fhcampuswien.carrental.carrentalservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarDto> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CarDto findById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        return convertToDto(car);
    }

    public CarDto create(CarDto carDto) {
        if (carDto.getId() != null) {
            throw new BadRequestException("Id field must be null for new entity");
        }
        Car car = convertToEntity(carDto);
        car = carRepository.save(car);
        return convertToDto(car);
    }

    public CarDto update(CarDto carDto) {
        if (carDto.getId() == null) {
            throw new BadRequestException("Id field cannot be null for update operation");
        }
        Optional<Car> optionalCar = carRepository.findById(carDto.getId());
        Car car = optionalCar.orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + carDto.getId()));
        car.setName(carDto.getName());
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        carRepository.save(car);
        return convertToDto(car);
    }

    public void deleteById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        carRepository.deleteById(car.getId());
    }

    private CarDto convertToDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getName(),
                car.getMake(),
                car.getModel(),
                car.getYear()
        );
    }

    private Car convertToEntity(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getName(),
                carDto.getMake(),
                carDto.getModel(),
                carDto.getYear()
        );
    }
}
