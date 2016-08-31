package edu.globant.universityfinder.webservice;

import edu.globant.universityfinder.model.University;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Defines the interface for the RESTfull Service. That provides Universities
 * related services.
 *
 * @author federico.calarco
 */
@Path("/universities")
public interface UniversityRESTService {

    /**
     * This method return all universities availables
     *
     * @return list of Universities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getUniversities();

    /**
     * This method return an University based on universityId
     *
     * @param universityId the universityId
     * @return an University
     */
    @GET
    @Path("/{universityId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUniversity(@PathParam("universityId") String universityId);

    /**
     * Add an University
     *
     * @param university
     * @return response with added university
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addUniversity(University university);

    /**
     * This method updates an university
     * @param universityId
     * @param university
     * @return
     */
    @PUT
    @Path("/{universityId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateUniversity(@PathParam("universityId") String universityId, University university);

    @DELETE
    @Path("/{universityId}")
    Response removerUniversity(@PathParam("universityId") String universityId);

    @DELETE
    Response removeAllUniversities();

}
