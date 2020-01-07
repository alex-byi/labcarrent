package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.OrderDao;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.OrderDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final OrderDataValidator validator = OrderDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    OrderDao orderDao;

    @Autowired
    CrashDao crashDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public List<Order> getAllOrders() throws ServiceException {
        List<Order> list;
        try {
            list = orderDao.getAllOrders();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkOrdersList(list)) {
            throw new ServiceException("List<Order> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    public boolean addNewOrder(Order order) throws ServiceException {
        if (validator.checkOrderInfo(order)) {
            throw new ServiceException("Order data is no valid");
        }
        try {
            return orderDao.addNewOrder(order);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<Order> userOrders(int id) throws ServiceException {
        List<Order> list;
        try {
            list = orderDao.userOrders(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (validator.checkOrdersList(list)) {
            throw new ServiceException("List<Order> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    public boolean setAmount(int orderId, int amount) throws ServiceException {
        try {
            return orderDao.setAmount(orderId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Order getOrderById(int id) throws ServiceException {
        Order order;
        try {
            order = orderDao.getOrderById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (validator.checkOrderInfo(order)) {
            throw new ServiceException("Order data is no valid");
        }
        return order;
    }

    @Override
    @Transactional
    public int setPayment(int orderId, int userId) throws ServiceException {
        try {
            Order order = orderDao.getOrderById(orderId);
            userDao.pay(order.getAmount(), userId);
            orderDao.setPayment(orderId);
            return order.getAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean setComplete(int orderId) throws ServiceException {
        try {
            return orderDao.setComplete(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean setCanceled(int orderId, String reason) throws ServiceException {
        try {
            orderDao.setCanceled(orderId);
            orderDao.setRejectReason(reason, orderId);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    @Transactional
    public boolean setCrash(int orderId, Crash crash) throws ServiceException {
        try {
            int crashId = crashDao.addCrash(crash);
            orderDao.setCrash(orderId, crashId);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public int pageCol() throws ServiceException {
        int pageCol;
        try {
            pageCol = orderDao.pageCol();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pageCol;
    }

}
