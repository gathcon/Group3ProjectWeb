//package performance;
//
//import static org.junit.Assert.*;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import javax.ejb.EJB;
//
//import loader.Loader;
//import loader.ValidationManager;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
//import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(Arquillian.class)
//public class SpeedTest {
//	@Deployment
//	public static WebArchive createTestArchive() {
//		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
//
//		return ShrinkWrap.create(WebArchive.class, "test.war")
//				.addPackages(true, "dao", "jaxrs", "model", "restApp", "loader")
//				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//				.addAsLibraries(resolver.artifact("org.mockito:mockito-all:1.9.5").resolveAsFiles())
//				.addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles());
//	}
//	
//	@EJB
//	Loader loader;
//
//	@Test
//	public void test() {
//		loader.loadFile("/home/conor/Downloads/Dataset_3A.xls");
//	}
//}
