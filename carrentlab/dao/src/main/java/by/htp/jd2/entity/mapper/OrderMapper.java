package by.htp.jd2.entity.mapper;

import by.htp.jd2.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {

        Order order = new Order();

        Date dateOrder = resultSet.getDate(2);
        Date startDate = resultSet.getDate(3);
        Date endDate = resultSet.getDate(4);
        boolean isPaid = resultSet.getBoolean(5);
        boolean isCrash = resultSet.getBoolean(6);
        int idCar = resultSet.getInt(7);
        int crashBill = resultSet.getInt(8);
        int idUser = resultSet.getInt(9);
        int dayCol = resultSet.getInt(11);
        int amount = resultSet.getInt(10);
        int id = resultSet.getInt(1);
        boolean isCanceled = resultSet.getBoolean(12);
        boolean isComplete = resultSet.getBoolean(13);
        String rejectReason = resultSet.getString(14);

        order.setDateOrder(dateOrder);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setPaid(isPaid);
        order.setCrash(isCrash);
        order.setIdCar(idCar);
        order.setCrashBill(crashBill);
        order.setIdUser(idUser);
        order.setDayCol(dayCol);
        order.setAmount(amount);
        order.setId(id);
        order.setCanceled(isCanceled);
        order.setComplete(isComplete);
        order.setRejectReason(rejectReason);


//        return new Order(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol,
//                            amount, id, isCanceled, isComplete, rejectReason);
        return order;
    }
}
