package edu.globant.universityfinder.webservice.impl;

import edu.globant.universityfinder.model.University;
import edu.globant.universityfinder.service.UniversityService;
import edu.globant.universityfinder.service.impl.UniversityServiceImpl;
import edu.globant.universityfinder.webservice.UniversityRESTService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by federico.calarco on 8/22/2016.
 */

public class UniversityRESTServiceImpl implements UniversityRESTService {

    private static final Logger logger = LogManager.getLogger(UniversityServiceImpl.class);

    UniversityService universityService;


    @Override
    public Response getUniversities() {

        List<University> universityList = (ArrayList) universityService.getAll();

        logger.debug("all universities size: " + universityList.size());

        return Response.ok(universityList).build();
    }

    @Override
    public Response getUniversity(String universityId) {

        logger.debug("universityId: " + universityId);

        //TODO: Verify if universityId is not null or empty

        //TODO: Parse move to other function

        Long id = Long.parseLong(universityId);

        University university = universityService.getById(id);

        //TODO: Verify if university if not null, empty..

        logger.debug("university returned is: " + university);
        return Response.ok(university).build();
    }

    @Override
    public Response addUniversity(University university) {

        logger.debug("Creating a new university: " + university.getName());

        Long universityId = universityService.save(university);

        logger.debug("New id generated: " + universityId);

        if(universityId == null){
            //TODO: Make exception
            throw new NullPointerException("Error creating university: " + university.getName());
        }

        return Response.created(URI.create("universities/" + universityId)).build();

    }

    @Override
    public Response updateUniversity(String universityId, University university) {
        return Response.ok(universityService.update(university)).build();
    }

    @Override
    public Response removerUniversity(String universityId) {

        universityService.delete(Long.valueOf(universityId));

        return Response.ok().build();
    }

    @Override
    public Response removeAllUniversities() {
        // universityService.removeAllUniversities();
        return null;
    }


    public UniversityService getUniversityService() {
        return universityService;
    }

    public void setUniversityService(UniversityService universityService) {
        this.universityService = universityService;
    }
}
