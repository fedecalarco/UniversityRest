package edu.globant.universityfinder.service;

import edu.globant.universityfinder.model.University;

import java.util.List;

/**
 * This class bla bla bla
 * Created by federico.calarco on 8/17/2016.
 */
public interface UniversityService {


    Long save(University university);

    University getById(Long universityId);

    List<University> getAll();

    void delete(Long universityId);

    University update(University university);

}