package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.CarDAO;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.entity.Car;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.CarDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService {

    private static final CarDataValidator validator = CarDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(CarServiceImpl.class.getName());


    @Autowired
    CarDAO carDao;


    @Override
    @Transactional
    public List<Car> getAllCars() throws ServiceException {
        List<Car> list;
        try {
            list = carDao.getAllCars();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarsList(list)) {
            throw new ServiceException("List<Car> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean addNewCar(Car car) throws ServiceException {
        if (validator.checkCarInfo(car)) {
            throw new ServiceException("Car data is no valid");
        }
        try {
            return carDao.addNewCar(car);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delCar(int id) throws ServiceException {
        try {
            return carDao.delCar(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean activateCar(int id) throws ServiceException {
        try {
            return carDao.activateCar(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws ServiceException {
        List<Car> list;
        try {
            list = carDao.getAllAvailableCars(startDate, endDate);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarsList(list)) {
            throw new ServiceException("List<Car> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    public Car getCarById(int id) throws ServiceException {
        Car car;
        try {
            car = carDao.getCarById(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarInfo(car)) {
            throw new ServiceException("Car data is no valid");
        }
        return car;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Car> getTransmissionCar(String transmission) throws ServiceException {
        List<Car> list;
        try {
            list = carDao.getTransmissionCar(transmission);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (!validator.checkTransmissionCarList(list, transmission)) {
            throw new ServiceException("List transmission car  no valid");
        }
        return list;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Car> getFuelCars(String fuel) throws ServiceException {
        List<Car> list;
        try {
            list = carDao.getFuelCars(fuel);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (!validator.checkFuelCarList(list, fuel)) {
            throw new ServiceException("List fuel car no valid");
        }
        return list;
    }

}
