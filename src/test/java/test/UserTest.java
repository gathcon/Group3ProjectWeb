package test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TableRow;
import model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.UserDAO;

@RunWith(Arquillian.class)
public class UserTest {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(TableRow.class, User.class, UserDAO.class)
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
        //log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }
}