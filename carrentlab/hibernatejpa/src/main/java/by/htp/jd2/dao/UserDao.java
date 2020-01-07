package by.htp.jd2.dao;

import by.htp.jd2.entity.User;

import java.util.List;

/**
 * @author alexey
 */
public interface UserDao {

    /**
     * user registration in system
     *
     * @param user
     * @return true if registration successfully
     */
    boolean registration(User user) throws DaoException;

    /**
     * Returns a list of all users from the database
     *
     * @param page int current page
     * @return List {@link User} objects
     */
    List<User> getAllUsers() throws DaoException;

    /**
     * Sets the activity flag to false
     *
     * @param id int User id
     * @return true if deactivate successfully
     */
    boolean delUser(int id) throws DaoException;

    /**
     * Sets the activity flag to true
     *
     * @param id int User id
     * @return true if activate successfully
     */
    boolean activateUser(int id) throws DaoException;

    /**
     * Pays the order by taking money from the account
     *
     * @param sum int total sum
     * @param id  int User id
     * @return true if payment complete successfully
     */
    boolean pay(int sum, int id) throws DaoException;

    /**
     * Adding money to the account of the user
     *
     * @param sum int sum
     * @param id  int User ID
     * @return true if adding money complete successfully
     */
    boolean addMoney(int sum, int id) throws DaoException;

    /**
     * Checks user for existence
     *
     * @param login String User login
     * @return true if User exists
     */
    boolean checkUser(String login) throws DaoException;

    /**
     * Gets the user by their ID
     *
     * @param id int User id
     * @return {@link User} object
     */
    User getUserById(int id) throws DaoException;

    /**
     * Gets the list of users after search
     *
     * @param searchLogin String login or half login name
     * @return List of {@link User} objects
     */
    List<User> searchUser(String searchLogin) throws DaoException;

    int usersCount() throws DaoException;

    User getUserByLogin(String login) throws DaoException;

    boolean changeEmail(int idUser, String email);

    boolean changePassword(int idUser, String password);

}
