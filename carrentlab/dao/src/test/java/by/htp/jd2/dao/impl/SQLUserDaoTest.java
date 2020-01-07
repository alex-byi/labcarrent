package by.htp.jd2.dao.impl;

import by.htp.jd2.entity.User;
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
public class SQLUserDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SQLUserDao sqlUserDao;


    @Test
    public void getAllUsers() {
        List<User> users = sqlUserDao.getAllUsers();
        Assert.assertNotNull(users);

    }

    @Test
    public void delUser() {
        sqlUserDao.delUser(2);
    }

}