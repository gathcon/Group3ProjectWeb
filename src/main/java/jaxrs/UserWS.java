package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.User;
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
    @Path("/all_Users/")
    public List<User> getUserList() {
        return usersDao.getAllUsers();
    }
    
    @POST
    public void addUsers(List<User> users) {
        usersDao.addUsers(users);
    }
    
}
