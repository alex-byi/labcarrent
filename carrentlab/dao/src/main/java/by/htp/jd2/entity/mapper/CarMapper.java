package by.htp.jd2.entity.mapper;

import by.htp.jd2.entity.Car;
//import by.htp.jd2.entity.TransmissionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {

        Car car = new Car();

        int id = resultSet.getInt(1);
        String name =  resultSet.getString(2);
        int price = resultSet.getInt(3);
        String fuel =  resultSet.getString(4);
        String color  = resultSet.getString(5);
        String body =  resultSet.getString(6);
        String transmissionType = resultSet.getString(7);
        boolean active = resultSet.getBoolean(8);

        car.setId(id);
        car.setName(name);
        car.setPrice(price);
        car.setFuel(fuel);
        car.setColor(color);
        car.setBody(body);
        car.setTransmissionType(transmissionType);
        car.setActive(active);

        return car;
    }
}
