package by.htp.jd2.entity.mapper;

import by.htp.jd2.entity.Crash;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrashMapper implements RowMapper<Crash> {


    @Override
    public Crash mapRow(ResultSet resultSet, int i) throws SQLException {

        Crash crash = new Crash();

        int id = resultSet.getInt(1);
        String damage = resultSet.getString(2);
        int amount= resultSet.getInt(3);
        int idCar= resultSet.getInt(5);
        int idUser= resultSet.getInt(6);
        boolean isComplete = resultSet.getBoolean(4);


        crash.setId(id);
        crash.setDamage(damage);
        crash.setAmount(amount);
        crash.setIdCar(idCar);
        crash.setIdUser(idUser);
        crash.setComplete(isComplete);

        return crash;
    }
}
