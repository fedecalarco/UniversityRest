package edu.globant.easymock.service.impl;

import edu.globant.easymock.dao.UniversityDao;
import edu.globant.easymock.model.University;
import edu.globant.easymock.service.UniversityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by federico.calarco on 8/17/2016.
 */
public class UniversityServiceImpl implements UniversityService {

    private static final Logger logger = LogManager.getLogger(UniversityServiceImpl.class);

    UniversityDao universityDao;

    @Override
    public University save(University university) {

        logger.debug("university saved: " + university);

        University uni = universityDao.save(university);

        logger.debug("university saved: " + uni);

        // TODO: IF NOT EMPTY, NULL, ETC

        return uni;
    }

    @Override
    public University getById(Long universityId) {
        return universityDao.getById(universityId);
    }

    @Override
    public List<University> getAll() {

        List<University> universities = universityDao.getAll();

        return universities;
    }

    @Override
    public void delete(Long universityId) {

        logger.debug("Delete universityId: " + universityId);

        universityDao.delete(universityId);


    }

    @Override
    public University update(University university) {

        String query = "UPDATE universities SET  WHERE universityId = :universityId ";

        return null;
    }

    public UniversityDao getUniversityDao() {
        return universityDao;
    }

    public void setUniversityDao(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }
}



