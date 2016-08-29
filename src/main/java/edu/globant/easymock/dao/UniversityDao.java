package edu.globant.easymock.dao;

import edu.globant.easymock.model.University;

import java.util.Collection;

/**
 * Created by federico.calarco on 8/18/2016.
 */
public interface UniversityDao {

    University save(University university);

    University getById(Long universityId);

    Collection<University> getAll();

    void delete(Long universityId);

    University update(University university);
}
