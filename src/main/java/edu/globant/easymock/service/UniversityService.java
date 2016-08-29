package edu.globant.easymock.service;

import edu.globant.easymock.model.University;

import java.util.List;

/**
 * This class bla bla bla
 * Created by federico.calarco on 8/17/2016.
 */
public interface UniversityService {


    University save(University university);

    University getById(Long universityId);

    List<University> getAll();

    void delete(Long universityId);

    University update(University university);

}