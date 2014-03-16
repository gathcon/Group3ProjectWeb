package test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

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
public class MyTest {
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(TableRow.class, User.class, UserDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    UserDAO userdao;
    
    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUserName("Jane");
        user.setPassword("password");
        user.setUserType("sysAdmin");
        userdao.addUser(user);
        assertNotNull(userdao.getUser("Jane"));
        //log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }
}