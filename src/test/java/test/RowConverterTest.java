package test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import loader.RowConverter;
import model.EntityType;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



	@RunWith(Arquillian.class)
	public class RowConverterTest {
		
		@Deployment
	    public static WebArchive createTestArchive() {
			MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	        
			return ShrinkWrap.create(WebArchive.class, "test.war")
					//put in packages for rowConverter, DAOManager, DAO's, loader (if testing "up"), 
	        		.addPackages(true, "dao", "jaxrs", "model", "restApp")
	                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    }
	
		@Inject 
		RowConverter rowConverter;
		EntityType e; 
		HSSFRow row; 
		
//		@Before
//		public void persistdb() {
//			//			failure = new Failure();
//			//			failure.setFailureId(failurePK);
//			//			failure.setDescription("Test Failure");
//			//			failuredao.addFailure(failure);
//		}
//
//		@After
//		public void removedb() {
////			failuredao.removeFailure(failure);
//		}

		@Test
		public void testConvertSwitch() {
			e=EntityType.BASEDATA;
			row=
			rowConverter.convert(row, e);
			
			
			
			
//			Failure f = failuredao.getFailure(failurePK);
//			assertNotNull(f);
//			assertNotNull(f.getFailureId());
//			assertNotNull(f.getDescription());
		}
		
}
