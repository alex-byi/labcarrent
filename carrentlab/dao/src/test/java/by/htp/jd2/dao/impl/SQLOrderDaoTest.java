package by.htp.jd2.dao.impl;

import by.htp.jd2.entity.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SQLOrderDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SQLOrderDao sqlOrderDao;



    @Test
    public void userOrders() {
        List<Order> userOrders =sqlOrderDao.userOrders(2);
        boolean expected = true;
        for (Order userOrder : userOrders) {
            if (userOrder.getIdUser() != 2) {
                expected = false;
                break;
            }
            break;
        }
        Assert.assertTrue(expected);
    }

}