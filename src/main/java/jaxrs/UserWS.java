package jaxrs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;
import dao.CustomResponse;
import dao.UserDAO;

@Path("/users")
@Stateless
@LocalBean
public class UserWS {

    @EJB
    private UserDAO usersDao;

    @GET
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userName") String userName) {
        return usersDao.getUser(userName);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList() {
        return usersDao.getAllUsers();
    }
    
    @GET
    @Path("/{uname}/{pword}/{type}")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUsers(@PathParam("uname") String userName, @PathParam("pword") String password, @PathParam("type") String userType) {
    	
    	User user = new User();
    	user.setUserName(userName);
    	user.setPassword(password);
    	user.setUserType(userType);
    	
    	Response.ResponseBuilder builder = null;
    	
    	CustomResponse response = usersDao.persist(user);
    	if(response == CustomResponse.OK) {
    		builder = Response.ok();
    	}
    	else if(response == CustomResponse.ENTITY_ALREADY_EXISTS) {
    		builder = Response.status(Response.Status.BAD_REQUEST).entity("Entity already exists");
    	}
    	return builder.build();
    }
    
}
