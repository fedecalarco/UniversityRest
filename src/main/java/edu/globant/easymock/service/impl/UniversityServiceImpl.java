package edu.globant.easymock.service.impl;

import edu.globant.easymock.dao.UniversityDao;
import edu.globant.easymock.model.University;
import edu.globant.easymock.service.UniversityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


/**
 * Created by federico.calarco on 8/17/2016.
 */
public class UniversityServiceImpl implements UniversityService {

    private static final Logger logger = LogManager.getLogger(UniversityServiceImpl.class);

    UniversityDao universityDao;


    @Override
    public University save(University university) {

        logger.info("university saved: " + university);

        University uni = universityDao.save(university);

        logger.info("university saved: " + uni);

        // TODO: IF NOT EMPTY, NULL, ETC

        return uni;
    }

    @Override
    public University getById(Long universityId) {
        return universityDao.getById(universityId);
    }

    @Override
    public Collection<University> getAll() {
        return universityDao.getAll();
    }

    @Override
    public void delete(Long universityId) {

    }

    @Override
    public University update(University university) {
        return null;
    }

    public UniversityDao getUniversityDao() {
        return universityDao;
    }

    public void setUniversityDao(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }
}



