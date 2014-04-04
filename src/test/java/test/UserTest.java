package test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import jaxrs.UserWS;
import model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.UserDAO;

@RunWith(Arquillian.class)
public class UserTest {
	
	@Deployment
    public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        
		return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackages(true, "dao", "jaxrs", "model", "restApp", "loader")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles());
    }

	@EJB
	private UserDAO userdao;

	private User user;

	@Test
	public void testRegisterUser() {
		user = new User();
		user.setUserName("Jane");
		user.setPassword("password");
		user.setUserType("sysAdmin");
		userdao.persist(user);
		assertNotNull(userdao.getUser("Jane"));
		userdao.remove(user);
		assertNull(userdao.getUser("Jane"));
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
		userdao.remove(userdao.getUser("user"));
		assertNull(userdao.getUser("user"));
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
		userdao.remove(userdao.getUser("user"));
		assertNull(userdao.getUser("user"));
		userdao.remove(userdao.getUser("user1"));
		assertNull(userdao.getUser("user1"));
		userdao.remove(userdao.getUser("user2"));
		assertNull(userdao.getUser("user2"));
	}
	
	@Test
	public void testAddDuplicate() {
		Response r1 = userWS.addUsers("user1", "password", "sysAdmin");
		Response r2 = userWS.addUsers("user1", "password", "sysAdmin");
		
		Response.ResponseBuilder builder = Response.ok();
		Response expected = builder.build();
		
		assertEquals(expected.getStatus(), r1.getStatus());
		assertNotEquals(r1.getStatus(), r2.getStatus());
	}
}