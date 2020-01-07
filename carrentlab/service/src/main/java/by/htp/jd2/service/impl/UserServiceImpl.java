package by.htp.jd2.service.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.validation.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final UserDataValidator validator = UserDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class.getName());


    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public boolean registration(User user) throws ServiceException {
        if (validator.checkUserInfo(user)) {
            throw new ServiceException("Registration info no valid");
        } else {
            try {
                return userDao.registration(user);
            } catch (DaoException e) {
                LOG.error(e);
                throw new ServiceException(e);
            }
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws ServiceException {
        List<User> list;
        try {
            list = userDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (!validator.checkUsersList(list)) {
            throw new ServiceException("List<User> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delUser(int id) throws ServiceException {
        try {
            return userDao.delUser(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean activateUser(int id) throws ServiceException {
        try {
            return userDao.activateUser(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean pay(int sum, int id) throws ServiceException {
        try {
            return userDao.pay(sum, id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean addMoney(int sum, int id) throws ServiceException {
        try {
            return userDao.addMoney(sum, id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public boolean checkUser(String login) throws ServiceException {
        if (validator.checkUserLogin(login)) {
            throw new ServiceException("Login no valid");
        }
        try {
            return userDao.checkUser(login);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public User getUserById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkUserInfo(user)) {
            throw new ServiceException("User no valid");
        }
        return user;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> searchUser(String searchLogin) throws ServiceException {
        List<User> list;
        try {
            list = userDao.searchUser(searchLogin);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    @Transactional
    public int usersCount() throws ServiceException {
        int count = 0;
        try{
            count = userDao.usersCount();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) throws ServiceException {
        User user;
        try{
            user = userDao.getUserByLogin(login);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean changeEmail(int idUser, String email) {
        return userDao.changeEmail(idUser, email);
    }

    @Override
    @Transactional
    public boolean changePassword(int idUser, String password) {
        return userDao.changePassword(idUser, password);
    }
}
