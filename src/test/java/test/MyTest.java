package test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;
import model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.UserDAO;

@RunWith(Arquillian.class)
public class MyTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(User.class, UserDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    UserDAO userdao;
    
    @Test
    public void testRegisterUser() throws Exception {
        User newMember = new User();
        newMember.setUserName("Jane");
        newMember.setPassword("password");
        newMember.setUserType("sysAdmin");
        userdao.addUser(newMember);
        assertNotNull(userdao.getUser("Jane"));
        //log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }
}