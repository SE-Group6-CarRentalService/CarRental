package at.fhcampuswien.carrental.carrentalservice.model;

import java.time.LocalDate;

public class Rental {

    private Long id;
    private Car car;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental() {
    }

    public Rental(Car car, User user, LocalDate startDate, LocalDate endDate) {
        this.car = car;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", car=" + car +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
