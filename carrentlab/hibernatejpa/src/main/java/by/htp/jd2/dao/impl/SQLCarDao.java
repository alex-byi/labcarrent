package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CarDAO;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class SQLCarDao implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> getAllCars() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
        carCriteriaQuery.select(carRoot);

        Query<Car> carQuery = session.createQuery(carCriteriaQuery);
        return carQuery.getResultList();
    }

    @Override
    public boolean addNewCar(Car car) throws DaoException {
        sessionFactory.getCurrentSession().save(car);
        return true;
    }

    @Override
    public boolean delCar(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Car> carCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Car.class);
        Root<Car> carRoot = carCriteriaUpdate.from(Car.class);
        carCriteriaUpdate.set("active", false);
        carCriteriaUpdate.where(criteriaBuilder.equal(carRoot.get("id"), id));

        session.createQuery(carCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean activateCar(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Car> carCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Car.class);
        Root<Car> carRoot = carCriteriaUpdate.from(Car.class);
        carCriteriaUpdate.set("active", true);
        carCriteriaUpdate.where(criteriaBuilder.equal(carRoot.get("id"), id));

        session.createQuery(carCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws DaoException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlStartDate, sqlEndDate;
        try {
            sqlStartDate = new java.sql.Date(dateFormat.parse(startDate).getTime());
            sqlEndDate = new java.sql.Date(dateFormat.parse(endDate).getTime());
        } catch (ParseException e) {
            throw new DaoException("GET CAR ERROR", e);
        }

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery("select * from cars where idcars NOT IN (select orders.cars_idcar " +
                "from orders where (enddate > :paramName1) OR ((startdate > :paramName2) AND (startdate < :paramName3)));").addEntity(Car.class);
        query.setParameter("paramName1", sqlStartDate);
        query.setParameter("paramName2", sqlStartDate);
        query.setParameter("paramName3", sqlEndDate);
        return query.list();
    }

    @Override
    public Car getCarById(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
        carCriteriaQuery.select(carRoot);
        carCriteriaQuery.where(criteriaBuilder.equal(carRoot.get("id"), id));

        Query<Car> carQuery = session.createQuery(carCriteriaQuery);
        return carQuery.getSingleResult();
    }

    @Override
    public List<Car> getTransmissionCar(String transmission) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
        carCriteriaQuery.select(carRoot);
        carCriteriaQuery.where(criteriaBuilder.equal(carRoot.get("transmissionType"), transmission));

        Query<Car> carQuery = session.createQuery(carCriteriaQuery);
        return carQuery.getResultList();
    }

    @Override
    public List<Car> getFuelCars(String fuel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
        carCriteriaQuery.select(carRoot);
        carCriteriaQuery.where(criteriaBuilder.equal(carRoot.get("fuel"), fuel));

        Query<Car> carQuery = session.createQuery(carCriteriaQuery);
        return carQuery.getResultList();
    }
}
