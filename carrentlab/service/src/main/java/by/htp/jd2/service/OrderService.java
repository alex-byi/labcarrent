package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.Order;

/**
 * Order service
 *
 * @author alexey
 */
public interface OrderService {

    /**
     * Service of Returns a list of all orders from the database.
     *
     * @param page int current page
     * @return List of Orders object
     */
    List<Order> getAllOrders() throws ServiceException;

    /**
     * Service of Adds a new order to the database
     *
     * @param order Order object
     * @return true if added successfully
     */
    boolean addNewOrder(Order order) throws ServiceException;

    /**
     * Service of Returns a list of orders for a specific user
     *
     * @param id int User ID
     * @return List of Order objects
     */
    List<Order> userOrders(int id) throws ServiceException;

    /**
     * Service of Sets the total amount for the order.
     *
     * @param orderId int Order ID
     * @param amount  int total amount
     * @return true if setting complete successfully
     */
    boolean setAmount(int orderId, int amount) throws ServiceException;

    /**
     * Service of Returns an Order object by its ID
     *
     * @param id int Order id
     * @return Order object
     */
    Order getOrderById(int id) throws ServiceException;

    /**
     * Service of Sets payment flag in true
     *
     * @param orderId int Order id
     * @return true if payment setting successfully
     */
    int setPayment(int orderId, int userId) throws ServiceException;

    /**
     * Service of Sets complete flag in true
     *
     * @param orderId int order id
     * @return true if setting successfully
     */
    boolean setComplete(int orderId) throws ServiceException;

    /**
     * Service of Sets complete flag in true
     *
     * @param orderId intt Order ID
     * @return true if setting successfully
     */
    boolean setCanceled(int orderId, String reason) throws ServiceException;

    /**
     * Service of Sets the crash to order
     *
     * @param orderId int Order ID
     * @return true if setting successfully
     */
    boolean setCrash(int orderId, Crash crash) throws ServiceException;

    /**
     * @return int number of pages
     */
    int pageCol() throws ServiceException;

}
