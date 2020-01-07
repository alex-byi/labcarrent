package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.User;
import by.htp.jd2.entity.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author alexey
 */

@Repository
public class SQLUserDao implements UserDao {


    private static final String REGISTRATION_USER = "INSERT INTO users(login, password, passportnumber, fullname, address, email) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users order by iduser desc;";
    private static final String DEL_USER = "UPDATE users SET active = '0' WHERE iduser = ?;";
    private static final String ACTIVATE_USER = "UPDATE users SET active = '1' WHERE iduser = ?;";
    private static final String PAY_BILL = "UPDATE users SET cash = cash - ? WHERE iduser = ?;";
    private static final String ADD_MONEY = "UPDATE users SET cash = cash + ? WHERE iduser = ?;";
    private static final String SELECT_LOGINS = "SELECT login FROM users;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE iduser = ?;";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
    private static final String SEARCH_USER = "select * from users where login like ?;";
    private static final String CHANGE_EMAIL = "UPDATE users SET email = ? WHERE iduser = ?;";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE iduser = ?;";

    @Autowired
    JdbcTemplate jdbcTemplate;


    private static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }


    /**
     * @param user object User
     * @return true if registration complete successfully
     */
    @Override
    public boolean registration(User user) {
        String hashPassword = md5Apache(user.getPassword());

        return jdbcTemplate.update(REGISTRATION_USER, user.getLogin(), hashPassword, user.getPassNum(),
                user.getFullName(), user.getAddress(), user.getEmail()) > 0;
    }


    /**
     * @return List of all users from database
     */
    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserMapper());
    }

    /**
     * @param id int id user
     * @return true if installation flag "active" to false successfully
     */
    @Override
    public boolean delUser(int id) {
        return jdbcTemplate.update(DEL_USER, id) > 0;
    }

    /**
     * @param sum int sum order receipt
     * @param id  int User id
     * @return true if payment successfully
     */
    @Override
    public boolean pay(int sum, int id) {
        return jdbcTemplate.update(PAY_BILL, sum, id) > 0;
    }

    /**
     * @param id int user id
     * @return true if activate successfully
     */
    @Override
    public boolean activateUser(int id) {
        return jdbcTemplate.update(ACTIVATE_USER, id) > 0;
    }

    /**
     * @param sum int sum
     * @param id  int user id
     * @return true if adding money complete successfully
     */
    @Override
    public boolean addMoney(int sum, int id) {
        return jdbcTemplate.update(ADD_MONEY, sum, id) > 0;
    }

    /**
     * @param login string login
     * @return false if user contain in userlist
     */
    @Override

    public boolean checkUser(String login) {
        List<String> logins = jdbcTemplate.queryForList(SELECT_LOGINS, String.class);
        return !logins.contains(login);
    }

    /**
     * @param id int user id
     * @return User object
     */
    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id}, new UserMapper());
    }

    /**
     * @param searchLogin String login or half login name
     * @return List of {@link User} objects
     */
    @Override
    public List<User> searchUser(String searchLogin) {
        return jdbcTemplate.query(SEARCH_USER, new Object[]{"%" + searchLogin + "%"}, new UserMapper());
    }

    @Override
    public int usersCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
        return Objects.requireNonNullElse(count, 0);
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.queryForObject(GET_USER_BY_LOGIN, new Object[]{login}, new UserMapper());

    }

    @Override
    public boolean changeEmail(int idUser, String email) {
        return jdbcTemplate.update(CHANGE_EMAIL, email, idUser) > 0;
    }

    @Override
    public boolean changePassword(int idUser, String password) {
        return jdbcTemplate.update(CHANGE_PASSWORD, password, idUser) > 0;
    }

}
