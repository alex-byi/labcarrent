package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.entity.Crash;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SqlCrashDao implements CrashDao {

    private static final String GET_LAST_ID = "SELECT MAX( idcrashbill ) FROM crashbill;";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Crash> getAllCrashs() throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Crash> crashCriteriaQuery = criteriaBuilder.createQuery(Crash.class);
        Root<Crash> crashRoot = crashCriteriaQuery.from(Crash.class);
        crashCriteriaQuery.select(crashRoot);

        Query<Crash> crashQuery = session.createQuery(crashCriteriaQuery);
        return crashQuery.getResultList();
    }

    @Override
    public int addCrash(Crash crash) throws DaoException {
        sessionFactory.getCurrentSession().save(crash);
        return lastInsertedId();
    }

    public int lastInsertedId() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(GET_LAST_ID, Number.class).getSingleResult().intValue();
    }

    @Override
    public List<Crash> getUsersCrashs(int idUser) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Crash> crashCriteriaQuery = criteriaBuilder.createQuery(Crash.class);
        Root<Crash> crashRoot = crashCriteriaQuery.from(Crash.class);
        crashCriteriaQuery.select(crashRoot);

        crashCriteriaQuery.where(criteriaBuilder.equal(crashRoot.get("idUser"), idUser));
        Query<Crash> crashQuery = session.createQuery(crashCriteriaQuery);
        return crashQuery.getResultList();
    }

    @Override
    public boolean setCrashPayment(int idCrash) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Crash> crashCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Crash.class);
        Root<Crash> crashRoot = crashCriteriaUpdate.from(Crash.class);
        crashCriteriaUpdate.set("isComplete", true);
        crashCriteriaUpdate.where(criteriaBuilder.equal(crashRoot.get("id"), idCrash));

        session.createQuery(crashCriteriaUpdate).executeUpdate();
        return true;
    }

    @Override
    public Crash getCrashById(int idCrash) throws DaoException {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Crash> crashCriteriaQuery = criteriaBuilder.createQuery(Crash.class);
        Root<Crash> crashRoot = crashCriteriaQuery.from(Crash.class);
        crashCriteriaQuery.select(crashRoot);
        crashCriteriaQuery.where(criteriaBuilder.equal(crashRoot.get("id"), idCrash));

        Query<Crash> crashQuery = session.createQuery(crashCriteriaQuery);
        return crashQuery.getSingleResult();
    }
}
