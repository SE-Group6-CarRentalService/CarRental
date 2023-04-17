package at.fhcampuswien.carrental.carrentalservice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private int id;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(int id, Car car, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double calculateRentalCost() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return days * car.getPricePerDay();
    }
}
