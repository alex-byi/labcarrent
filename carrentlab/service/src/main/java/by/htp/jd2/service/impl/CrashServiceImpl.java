package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.CrashDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrashServiceImpl implements CrashService {

    private static final CrashDataValidator validator = CrashDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(CrashServiceImpl.class.getName());

    @Autowired
    CrashDao crashDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public List<Crash> getAllCrashs() throws ServiceException {
        List<Crash> list;
        try {
            list = crashDao.getAllCrashs();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCrashsList(list)) {
            throw new ServiceException("List<Crash> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public int addCrash(Crash crash) throws ServiceException {
        int id;
//        if (validator.checkCrashInfo(crash)) {
//            throw new ServiceException("Crash data is no valid");
//        }
        try {
            id = crashDao.addCrash(crash);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    @Transactional
    public List<Crash> getUsersCrashs(int idUser) throws ServiceException {
        List<Crash> list;
        try {
            list = crashDao.getUsersCrashs(idUser);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCrashsList(list)) {
            throw new ServiceException("List<Crash> no valid");
        }
        return list;
    }

    @Override
    @Transactional
    public int setCrashPayment(int idCrash) throws ServiceException {
        try {
            Crash crash = crashDao.getCrashById(idCrash);
            crashDao.setCrashPayment(idCrash);
            userDao.pay(crash.getAmount(), crash.getIdUser());
            return crash.getAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Crash getCrashById(int idCrash) throws ServiceException {
        try {
            return crashDao.getCrashById(idCrash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}
