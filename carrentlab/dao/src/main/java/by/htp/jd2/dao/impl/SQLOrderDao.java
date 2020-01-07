package by.htp.jd2.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.OrderDao;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.mapper.OrderMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author alexey
 */
@Repository
public class SQLOrderDao implements OrderDao {

    private static final Logger LOG = LogManager.getLogger(SQLOrderDao.class.getName());

    private static final String GET_ALL_ORDERS = "SELECT * FROM orders order by idorder desc;";
    private static final String ADD_ORDER = "INSERT INTO orders (dateorder, startdate, enddate, cars_idcar, users_iduser, amount, dayCol) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDERS_BY_ID_USER = "SELECT * FROM orders WHERE users_iduser = ?;";
    private static final String SET_AMOUNT = "UPDATE orders SET amount = ? WHERE idorder = ?;";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE idorder = ?;";
    private static final String SET_PAYMENT = "UPDATE orders SET ispaid = '1' WHERE idorder = ?;";
    private static final String SET_COMPLETE = "UPDATE orders SET iscomplete = '1' WHERE idorder = ?;";
    private static final String SET_CANCELED = "UPDATE orders SET iscanceled = '1' WHERE idorder = ?;";
    private static final String SET_REJECT_REASON = "UPDATE orders SET reject_reason = ? WHERE idorder = ?;";
    private static final String SET_CRASHBILL_ID = "UPDATE orders SET crashbill_idcrashbill = ?, iscrash = '1' WHERE idorder = ?;";
    private static final String GET_PAGE_COL = "SELECT count(idorder) FROM orders;";

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
//     * @param page int page number
     * @return List of orders
     */
    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query(GET_ALL_ORDERS, new OrderMapper());
    }

    /**
     * @param order order object
     * @return true if adding is successfully or false if no
     */
    @Override
    public boolean addNewOrder(Order order) throws DaoException {

        java.sql.Date dateOrder, startDate, endDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            dateOrder = convertDateToSql(order.getDateOrder());
            startDate = convertDateToSql(order.getStartDate());
            endDate = convertDateToSql(order.getEndDate());

        return jdbcTemplate.update(ADD_ORDER, dateOrder, startDate, endDate, order.getIdCar(), order.getIdUser(),
                order.getAmount(), order.getDayCol()) > 0;
    }

    private static java.sql.Date convertDateToSql(java.util.Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }


    /**
     * @param idUser int user id
     * @return List of orders for current user
     */
    @Override
    public List<Order> userOrders(int idUser) {
        return jdbcTemplate.query(GET_ORDERS_BY_ID_USER, new Object[]{idUser}, new OrderMapper());
    }

    /**
     * @param orderId int order id
     * @param amount  int amount
     * @return true if set complete successfully or false if no
     */
    @Override
    public boolean setAmount(int orderId, int amount) {
        return jdbcTemplate.update(SET_AMOUNT, amount, orderId) > 0;
    }

    /**
     * @param id int order id
     * @return order object
     */
    @Override
    public Order getOrderById(int id) {
        return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new Object[]{id}, new OrderMapper());
    }

    /**
     * @param orderId int order id
     * @return true if payment complete successfully
     */
    @Override
    public boolean setPayment(int orderId) {
        return jdbcTemplate.update(SET_PAYMENT, orderId) > 0;
    }

    /**
     * @param orderId int order id
     * @return true if setting complete flag complete successfully
     */
    @Override
    public boolean setComplete(int orderId) {
        return jdbcTemplate.update(SET_COMPLETE, orderId) > 0;
    }

    /**
     * @param orderId int order id
     * @return true if setting canceled flag complete successfully
     */
    @Override
    public boolean setCanceled(int orderId) {
        return jdbcTemplate.update(SET_CANCELED, orderId) > 0;
    }

    /**
     * @param reason  string reason of addition bill
     * @param orderId int order id
     * @return true if setting reason of reject complete successfully
     */
    @Override
    public boolean setRejectReason(String reason, int orderId) {
        return jdbcTemplate.update(SET_REJECT_REASON, reason, orderId) > 0;
    }

    /**
     * @param orderId int order id
     * @param crashId int crash id
     * @return true if setting crash flag complete successfully
     */
    @Override
    public boolean setCrash(int orderId, int crashId) {
        return jdbcTemplate.update(SET_CRASHBILL_ID, crashId, orderId) > 0;
    }

    /**
     * @return number of page from database
     */
    @Override
    public int pageCol() {
        Integer pageCol = jdbcTemplate.queryForObject(GET_PAGE_COL, Integer.class);
        return Objects.requireNonNullElse(pageCol, 0);
    }
}
