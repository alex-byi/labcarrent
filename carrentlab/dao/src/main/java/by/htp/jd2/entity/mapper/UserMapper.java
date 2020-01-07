package by.htp.jd2.entity.mapper;

import by.htp.jd2.entity.User;
//import by.htp.jd2.entity.UserType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();

        String login = resultSet.getString(4);
        String password = resultSet.getString(2);
        String fullName = resultSet.getString(6);
        String passNum = resultSet.getString(3);
        String email = resultSet.getString(7);
        String address = resultSet.getString(8);
        String type = resultSet.getString(1);
        int cash = resultSet.getInt(10);
        boolean active = resultSet.getBoolean(9);
        int id = resultSet.getInt(5);

        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPassNum(passNum);
        user.setEmail(email);
        user.setAddress(address);
        user.setType(type);
        user.setCash(cash);
        user.setActive(active);
        user.setId(id);

//        return new User(login, password, fullName, passNum, email, address, cash, type, active, id);
        return user;
    }
}
