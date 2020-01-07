package by.htp.jd2.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    public Car() {
    }

    @Id
    @Column(name = "idcars")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "color")
    private String color;

    @Column(name = "body")
    private String body;

    @Column(name = "transmission")
    private String transmissionType;

    @Column(name = "active")
    private boolean active;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                price == car.price &&
                active == car.active &&
                Objects.equals(name, car.name) &&
                Objects.equals(fuel, car.fuel) &&
                Objects.equals(color, car.color) &&
                Objects.equals(body, car.body) &&
                Objects.equals(transmissionType, car.transmissionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, fuel, color, body, transmissionType, active);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fuel='" + fuel + '\'' +
                ", color='" + color + '\'' +
                ", body='" + body + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", active=" + active +
                '}';
    }
}
