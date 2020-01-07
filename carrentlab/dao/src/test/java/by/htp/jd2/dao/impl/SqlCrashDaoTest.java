package by.htp.jd2.dao.impl;

import by.htp.jd2.entity.Crash;
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
public class SqlCrashDaoTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SqlCrashDao sqlCrashDao;

    @Test
    public void getAllCrashs() {
        List<Crash> crashs = sqlCrashDao.getAllCrashs();
        Assert.assertNotNull(crashs);
    }


}