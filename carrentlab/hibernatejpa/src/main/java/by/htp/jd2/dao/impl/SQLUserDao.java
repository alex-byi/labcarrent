package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SQLUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean registration(User user) throws DaoException {
        return false;
    }

    @Override
    public List<User> getAllUsers() throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);

        Query<User> userQuery = session.createQuery(userCriteriaQuery);
        return userQuery.getResultList();
    }

    @Override
    public boolean delUser(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("active", false);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean activateUser(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("active", true);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean pay(int sum, int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        int userCash = getUserById(id).getCash();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("cash", userCash - sum);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean addMoney(int sum, int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        int userCash = getUserById(id).getCash();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("cash", userCash + sum);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean checkUser(String login) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        Query<User> userQuery = session.createQuery(userCriteriaQuery);
        List<User> users = userQuery.getResultList();
        List<String> logins = new ArrayList<>();
        for (User user : users) {
            logins.add(user.getLogin());
        }
        return !logins.contains(login);
    }

    @Override
    public User getUserById(int id) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        userCriteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));

        Query<User> userQuery = session.createQuery(userCriteriaQuery);
        return userQuery.getSingleResult();
    }

    @Override
    public List<User> searchUser(String searchLogin) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
        userCriteriaQuery.where(criteriaBuilder.like(userRoot.get("login"), "%" + searchLogin + "%"));

        Query<User> userQuery = session.createQuery(userCriteriaQuery);
        return userQuery.getResultList();
    }

    @Override
    public int usersCount() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from User", Number.class).getSingleResult().intValue();
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :paramName");
        query.setParameter("paramName", login);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean changeEmail(int idUser, String email) {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("email", email);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), idUser));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public boolean changePassword(int idUser, String password) {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate <User> userCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = userCriteriaUpdate.from(User.class);
        userCriteriaUpdate.set("password", password);
        userCriteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), idUser));

        session.createQuery(userCriteriaUpdate).executeUpdate();
        return true;
    }
}
