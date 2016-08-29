package edu.globant.easymock.webservice.impl;

import edu.globant.easymock.model.University;
import edu.globant.easymock.service.UniversityService;
import edu.globant.easymock.service.impl.UniversityServiceImpl;
import edu.globant.easymock.webservice.UniversityRESTService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.PathParam;
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

        List<University> universityList = (ArrayList)universityService.getAll();

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

        universityService.save(university);


        return Response.created(URI.create("universities/"+university.getId())).build();

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
