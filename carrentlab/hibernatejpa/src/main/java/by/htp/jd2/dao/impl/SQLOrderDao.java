package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.OrderDao;
import by.htp.jd2.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SQLOrderDao implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getAllOrders() throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Order> orderCriteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderCriteriaQuery.from(Order.class);
        orderCriteriaQuery.select(orderRoot);

        Query<Order> orderQuery = session.createQuery(orderCriteriaQuery);
        return orderQuery.getResultList();
    }

    @Override
    public boolean addNewOrder(Order order) throws DaoException {
        sessionFactory.getCurrentSession().save(order);
        return true;
    }

    @Override
    public List<Order> userOrders(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Order> orderCriteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderCriteriaQuery.from(Order.class);
        orderCriteriaQuery.select(orderRoot);
        orderCriteriaQuery.where(criteriaBuilder.equal(orderRoot.get("idUser"), id));

        Query<Order> orderQuery = session.createQuery(orderCriteriaQuery);
        return orderQuery.getResultList();
    }

    @Override
    public boolean setAmount(int orderId, int amount) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("amount", amount);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public Order getOrderById(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Order> orderCriteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderCriteriaQuery.from(Order.class);
        orderCriteriaQuery.select(orderRoot);
        orderCriteriaQuery.where(criteriaBuilder.equal(orderRoot.get("id"), id));

        Query<Order> orderQuery = session.createQuery(orderCriteriaQuery);
        return orderQuery.getSingleResult();
    }

    @Override
    public boolean setPayment(int orderId) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("isPaid", true);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean setComplete(int orderId) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("isComplete", true);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean setCanceled(int orderId) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("isCanceled", true);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean setRejectReason(String reason, int orderId) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("rejectReason", reason);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean setCrash(int orderId, int crashId) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Order> orderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> orderRoot = orderCriteriaUpdate.from(Order.class);
        orderCriteriaUpdate.set("isCrash", true);
        orderCriteriaUpdate.where(criteriaBuilder.equal(orderRoot.get("id"), orderId));

        session.createQuery(orderCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public int pageCol() throws DaoException {
        return 0;
    }
}
