package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import jaxrs.UserWS;
import model.TableRow;
import model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.UserDAO;

@RunWith(Arquillian.class)
public class UserTest {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(UserWS.class, TableRow.class, User.class, UserDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
	private UserDAO userdao;
    
    private User user;
    
    @Test
    public void testRegisterUser() throws Exception {
        user = new User();
        user.setUserName("Jane");
        user.setPassword("password");
        user.setUserType("sysAdmin");
        userdao.addUser(user);
        assertNotNull(userdao.getUser("Jane"));
        userdao.removeUser(user);
    }
    
    @EJB
    UserWS userWS;
    
    @Test
    public void testAddUser() {
    	userWS.addUsers("user", "password", "sysAdmin");
    	assertNotNull(userWS.getUser("user"));
    	assertEquals(userWS.getUser("user").getUserName(), "user");
    	assertEquals(userWS.getUser("user").getPassword(), "password");
    	assertEquals(userWS.getUser("user").getUserType(), "sysAdmin");
    	userdao.removeUser(userdao.getUser("user"));
    }
    
    @Test
    public void testGetAllUsers() {
    	userWS.addUsers("user", "password", "sysAdmin");
    	userWS.addUsers("user1", "password", "sysAdmin");
    	userWS.addUsers("user2", "password", "sysAdmin");
    	assertNotNull(userWS.getUser("user"));
    	assertNotNull(userWS.getUser("user1"));
    	assertNotNull(userWS.getUser("user2"));
    	assertEquals(userWS.getUserList().size(), 3);
    	userdao.removeUser(userdao.getUser("user"));
    	userdao.removeUser(userdao.getUser("user1"));
    	userdao.removeUser(userdao.getUser("user2"));
    }
}