package at.fhcampuswien.carrental.carrentalservice.service.mapper;

import at.fhcampuswien.carrental.carrentalservice.dto.CarDto;
import at.fhcampuswien.carrental.carrentalservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto carToCarDto(Car car) {
        if (car == null) {
            return null;
        }

        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        carDto.setPricePerDay(car.getPricePerDay());

        return carDto;
    }

    @Override
    public Car carDtoToCar(CarDto carDto) {
        if (carDto == null) {
            return null;
        }

        Car car = new Car();
        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setPricePerDay(carDto.getPricePerDay());

        return car;
    }
}
