package by.htp.jd2.dao.impl;


import java.util.List;
import java.util.Objects;

import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.mapper.CrashMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author alexey
 */
@Repository
public class SqlCrashDao implements CrashDao {

    private static final Logger LOG = LogManager.getLogger(SqlCrashDao.class.getName());

    private static final String GET_ALL_CRASH_BILLS = "SELECT * FROM crashbill;";
    private static final String ADD_CRASH = "INSERT INTO crashbill (description, amount, cars_idcars , users_iduser) VALUES(?, ?, ?, ?);";
    private static final String GET_LAST_ID = "SELECT MAX( idcrashbill ) FROM crashbill;";
    private static final String GET_USERS_CRASH_BILLS = "select * from crashbill where users_iduser = ?;";
    private static final String SET_CRASH_PAYMENT = "UPDATE crashbill set complete = '1' WHERE idcrashbill = ?;";
    private static final String GET_CRASH_BY_ID = "SELECT * FROM crashbill WHERE idcrashbill = ?;";


    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * @return List of all additional bills from database
     */
    @Override
    public List<Crash> getAllCrashs() {
        return jdbcTemplate.query(GET_ALL_CRASH_BILLS, new CrashMapper());
    }

    /**
     * @param crash Crash object
     * @return id of added crash
     */
    @Override
    public int addCrash(Crash crash)  {
        jdbcTemplate.update(ADD_CRASH, crash.getDamage(), crash.getAmount(), crash.getIdCar(),
                crash.getIdUser());
        Integer lastId = jdbcTemplate.queryForObject(GET_LAST_ID, Integer.class);
        return Objects.requireNonNullElse(lastId, 0);
    }

    /**
     * @param idUserC int id user
     * @return List of crashs of concrete user
     */
    @Override
    public List<Crash> getUsersCrashs(int idUserC)  {
        return jdbcTemplate.query(GET_USERS_CRASH_BILLS, new Object[]{idUserC}, new CrashMapper());
    }

    /**
     * @param idCrash int crash id
     * @return true if crash payment install successfully or false if no
     */
    @Override
    public boolean setCrashPayment(int idCrash)  {
        return jdbcTemplate.update(SET_CRASH_PAYMENT, idCrash) > 0 ;
    }

    /**
     * @param idCrash int crash id
     * @return crash object
     */
    @Override
    public Crash getCrashById(int idCrash) {
        return jdbcTemplate.queryForObject(GET_CRASH_BY_ID, new Object[] {idCrash}, new CrashMapper());
    }

}
