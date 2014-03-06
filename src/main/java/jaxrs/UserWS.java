package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    public User getUser(@PathParam("userName") String userName) {
        return usersDao.getUser(userName);
    }
    
    @POST
    public void addUsers(List<User> users) {
        usersDao.addUsers(users);
    }
    
}
