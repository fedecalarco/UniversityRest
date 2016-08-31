package edu.globant.universityfinder.dao;

import edu.globant.universityfinder.model.University;


import java.util.List;

/**
 * Created by federico.calarco on 8/18/2016.
 */
public interface UniversityDao {

    University save(University university);

    University getById(Long universityId);

    List<University> getAll();

    void delete(Long universityId);

    University update(University university);
}
