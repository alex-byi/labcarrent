package by.htp.jd2.dao.impl;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.TransmissionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SQLCarDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SQLCarDao sqlCarDao;

    @Test
    public void getAllCars() {
        List<Car> cars = sqlCarDao.getAllCars();
        Assert.assertNotNull(cars);
    }


    @Test
    public void delCar() {
        sqlCarDao.delCar(1);
    }


    @Test
    public void activateCar() {
        sqlCarDao.activateCar(1);

    }

    @Test
    public void getTransmissionCar() {
        List<String> transmissionTypes = new ArrayList<>();
        transmissionTypes.add("MANUAL");
        transmissionTypes.add("AUTOMATIC");
        boolean expected = true;
        for (String transmissionType : transmissionTypes) {
            List<Car> transmissionCars = sqlCarDao.getTransmissionCar(transmissionType);
            for (Car car : transmissionCars) {
                if (!car.getTransmissionType().toString().equals(transmissionType)) {
                    expected = false;
                    break;
                }
                break;
            }
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void getFuelCars() {
        List<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("дизель");
        fuelTypes.add("бензин");
        fuelTypes.add("электричество");
        boolean expected = true;

        for (String fuelType : fuelTypes) {
            List<Car> fuelCars = sqlCarDao.getFuelCars(fuelType);
            for (Car fuelCar : fuelCars) {
                if (!fuelCar.getFuel().equals(fuelType)) {
                    expected = false;
                    break;
                }
                break;
            }
        }
        Assert.assertTrue(expected);
    }
}