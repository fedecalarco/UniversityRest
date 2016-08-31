package edu.globant.universityfinder.service.impl;

import edu.globant.universityfinder.dao.UniversityDao;
import edu.globant.universityfinder.model.University;
import edu.globant.universityfinder.service.UniversityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Created by federico.calarco on 8/17/2016.
 */
public class UniversityServiceImpl implements UniversityService {

    private static final Logger logger = LogManager.getLogger(UniversityServiceImpl.class);

    UniversityDao universityDao;

    @Override
    public Long save(University university) {

        logger.debug("University to save: " + university.getName());

        University uni = universityDao.save(university);

        logger.debug("University saved: " + uni.getName());

        Long universityId = uni.getId();

        // TODO: IF NOT EMPTY, NULL, ETC

        if(universityId == null){
            //TODO: Make an exception
            throw new NullPointerException("universityId null");
        }

        return universityId;
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



