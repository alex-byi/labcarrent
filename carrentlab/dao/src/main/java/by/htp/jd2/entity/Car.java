package by.htp.jd2.entity;


import java.io.Serializable;
import java.util.Objects;


/**
 * Car entity
 *
 * @author alexey
 */
public final class Car implements Serializable {

    private static final long serialVersionUID = 910894813499440171L;
    private int id;
    private String name;
    private int price;
    private String fuel;
    private String color;
    private String body;
    private String transmissionType;
    private boolean active;

    public Car() {
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
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
