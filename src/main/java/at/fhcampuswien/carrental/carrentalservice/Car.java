package at.fhcampuswien.carrental.carrentalservice;

public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private boolean available;

    public Car(int id, String make, String model, int year, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
