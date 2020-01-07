package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CarDAO;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.mapper.CarMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author alexey
 */
@Repository
public class SQLCarDao implements CarDAO {
    private static final Logger LOG = LogManager.getLogger(SQLCarDao.class.getName());

    private static final String GET_ALL_CAR = "SELECT * FROM cars order by idcars desc;";
    private static final String ADD_CAR = "INSERT INTO cars(name, price, fuel, color, body, transmission)"
            + " VALUES(?, ?, ?, ?, ?, ?)";
    private static final String DEL_CAR = "UPDATE cars SET active = '0' WHERE idcars = ?;";
    private static final String ACTIVATE_CAR = "UPDATE cars SET active = '1' WHERE idcars = ?;";
    private static final String GET_ALL_AVAILABLE_CARS_ID = "select * from cars where idcars NOT IN (select orders.cars_idcar from orders where (enddate > ?) OR ((startdate > ?) AND (startdate < ?)));";
    private static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE idcars = ?;";
    private static final String GET_TRANSMISSION_CARS = "SELECT * FROM cars WHERE transmission = ?;";
    private static final String GET_FUEL_CARS = "SELECT * FROM cars WHERE fuel = ?;";

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * @return List of all cars from database
     */
    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query(GET_ALL_CAR, new CarMapper());
    }

    /**
     * @param car Car object
     * @return true if adding successfully or false if no
     */
    @Override
    public boolean addNewCar(Car car) {
        return jdbcTemplate.update(ADD_CAR, car.getName(), car.getPrice(), car.getFuel(),
                car.getColor(), car.getBody(), car.getTransmissionType()) > 0;
    }

    /**
     * @param id int car id
     * @return true if deactivate successfully or false if no
     */
    @Override
    public boolean delCar(int id) {
        return jdbcTemplate.update(DEL_CAR, id) > 0;
    }

    /**
     * @param startDate string date of start rent in format YYYY-MM-DD
     * @param endDate   string date of end rent in format YYYY-MM-DD
     * @return List of all available cars from database
     */
    @Override
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws DaoException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlStartDate, sqlEndDate;
        try {
            sqlStartDate = new Date(dateFormat.parse(startDate).getTime());
            sqlEndDate = new java.sql.Date(dateFormat.parse(endDate).getTime());

        } catch (ParseException e) {
            LOG.error(e);
            throw new DaoException("ADD ORDER ERROR", e);
        }
        return jdbcTemplate.query(GET_ALL_AVAILABLE_CARS_ID, new Object[]{sqlStartDate, sqlStartDate, sqlEndDate}, new CarMapper());
    }


    /**
     * @param id int car id
     * @return Car object
     */
    @Override
    public Car getCarById(int id) {
        return jdbcTemplate.queryForObject(GET_CAR_BY_ID,  new Object[]{id}, new CarMapper());
    }


    /**
     * @param id int car id
     * @return true if activate successfully or false if no
     */
    @Override
    public boolean activateCar(int id) {
        return jdbcTemplate.update(ACTIVATE_CAR, id) > 0;
    }

    /**
     * @param transmission string transmission type
     * @return List all car with transmission type
     */
    @Override
    public List<Car> getTransmissionCar(String transmission) {
        return jdbcTemplate.query(GET_TRANSMISSION_CARS, new Object[]{transmission}, new CarMapper());
    }


    /**
     * @param fuel string fuel type
     * @return List all car with fuel type
     */
    @Override
    public List<Car> getFuelCars(String fuel) {
        return jdbcTemplate.query(GET_FUEL_CARS, new Object[]{fuel}, new CarMapper());
    }

}
