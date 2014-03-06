package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import model.Failure;
import dao.FailureDAO;

@Path("/failures")
@Stateless
@LocalBean
public class FailureWS {

    @EJB
    private FailureDAO failuresDao;

    @GET
    @Path("/{id}")
    public Failure getFailure(@PathParam("id") int id) {
        return failuresDao.getFailure(id);
    }
    
    @POST
    public void addFailures(List<Failure> failures) {
        failuresDao.addFailures(failures);
    }
    
}
