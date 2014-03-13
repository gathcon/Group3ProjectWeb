package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.User;
import dao.UserDAO;

@Path("/session")
@Stateless
@LocalBean
public class LoginWS {

    @EJB
    private UserDAO usersDao;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean getUser(User user) {
		User tempUser = usersDao.getUser(user.getUserName());
		if (tempUser.getPassword().equals(user.getPassword())) {
			return true;
		} else
			return false;
    }
    
    @GET
    @Path("/logout")
    public void getUserList() {
    }
    
}
