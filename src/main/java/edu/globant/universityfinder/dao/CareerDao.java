package edu.globant.universityfinder.dao;

import edu.globant.universityfinder.model.Career;

import java.util.Collection;

/**
 * Created by federico.calarco on 8/24/2016.
 */
public interface CareerDao {

    Career save (Career career);

    Career getById(Long career);

    Collection<Career> getAll();

    void delete(Long career);

    Career update(Career university);

}
