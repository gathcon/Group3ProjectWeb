package test;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.ejb.EJB;

import loader.RowConverter;
import model.Base_Data;
import model.EntityType;
import model.Event_Cause;
import model.Event_CausePK;
import model.Failure;
import model.Operator;
import model.OperatorPK;
import model.TableRow;
import model.User_Equipment;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.Event_CauseDAO;
import dao.FailureDAO;
import dao.OperatorDAO;
import dao.User_EquipmentDAO;
  
  
    @RunWith(Arquillian.class)
    public class RowConverterTest {

        @Deployment
        public static WebArchive createTestArchive() {
            MavenDependencyResolver resolver = DependencyResolvers.use(
                    MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
              
            return ShrinkWrap
                    .create(WebArchive.class, "test.war")
                    .addPackages(true, "dao", "jaxrs", "loader", "model", "restApp", "validation", "redundant")
                    .addAsResource("META-INF/persistence.xml",
                            "META-INF/persistence.xml")
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                    .addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles())
                    .addAsLibraries(resolver.artifact("org.mockito:mockito-all:1.9.5").resolveAsFiles());
        }
      
        @EJB
        RowConverter rowConverter;
          
        @Test
        public void testConvertFailureCase() {
          
            EntityType e =EntityType.FAILURE;
              
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
              
            row.createCell(0).setCellValue(10);
            row.createCell(1).setCellValue("String");
              
            TableRow actual = rowConverter.convert(row, e);
            Failure expected = new Failure();
              
            assertEquals(expected.getClass(), actual.getClass());
        }
          
        @Test
        public void testConvertOperatorCase() {
          
            EntityType e=EntityType.OPERATOR;
              
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue(10);
            row.createCell(1).setCellValue(10);
            row.createCell(2).setCellValue("String");
            row.createCell(3).setCellValue("String");
              
            TableRow actual = rowConverter.convert(row, e);
            Operator expected = new Operator();
              
              
            assertEquals(expected.getClass(), actual.getClass());
        }
          
        @Test
        public void testConvertUserEquipmentCase() {
          
            EntityType e=EntityType.USEREQUIPMENT;
              
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue(10);
            row.createCell(1).setCellValue("String");
            row.createCell(2).setCellValue("String");
            row.createCell(3).setCellValue("String");
            row.createCell(4).setCellValue("String");
            row.createCell(5).setCellValue("String");
            row.createCell(6).setCellValue("String");
            row.createCell(7).setCellValue("String");
            row.createCell(8).setCellValue("String");
              
            TableRow actual = rowConverter.convert(row, e);
            User_Equipment expected = new User_Equipment();
              
              
            assertEquals(expected.getClass(), actual.getClass());
        }
          
        @Test
        public void testConvertEventCauseCase() {
          
            EntityType e=EntityType.EVENTCAUSE;
              
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue(10);
            row.createCell(1).setCellValue(10);
            row.createCell(2).setCellValue("String");
              
            TableRow actual = rowConverter.convert(row, e);
            Event_Cause expected = new Event_Cause();
              
              
            assertEquals(expected.getClass(), actual.getClass());
        }
               
        @Test
        public void testBaseDataCase() {
        
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            EntityType e = EntityType.BASEDATA;
            
           
            row.createCell(0).setCellValue(new Date());
            row.createCell(1).setCellValue(10);
            row.createCell(2).setCellValue(10);
            row.createCell(3).setCellValue(10);
            row.createCell(4).setCellValue(10);
            row.createCell(5).setCellValue(10);
            row.createCell(6).setCellValue(10);
            row.createCell(7).setCellValue(10);
            row.createCell(8).setCellValue(10);
            row.createCell(9).setCellValue("");
            row.createCell(10).setCellValue(10);
            row.createCell(11).setCellValue(10);
            row.createCell(12).setCellValue(10);
            row.createCell(13).setCellValue(10);
            
            Failure mockedFailure = mock(Failure.class);
            Operator mockedOperator = mock(Operator.class);
            User_Equipment mockedUserEquipment = mock(User_Equipment.class);
            Event_Cause mockedEventCause = mock(Event_Cause.class);
            
            FailureDAO mockedFailureDAO = mock(FailureDAO.class);
            OperatorDAO mockedOperatorDAO = mock(OperatorDAO.class);
            Event_CauseDAO mockedEvent_CauseDAO = mock(Event_CauseDAO.class);
            User_EquipmentDAO mockedUser_EquipmentDAO = mock(User_EquipmentDAO.class);
            
            rowConverter.setEvent_CauseDAO(mockedEvent_CauseDAO);
            rowConverter.setFailureDAO(mockedFailureDAO);
            rowConverter.setOperatorDAO(mockedOperatorDAO);
            rowConverter.setUser_EquipmentDAO(mockedUser_EquipmentDAO);
            
            Event_CausePK mockedEvent_CausePK = mock(Event_CausePK.class);
            OperatorPK mockedOperatorPK = mock(OperatorPK.class);
            
            when(mockedFailureDAO.getFailure(10)).thenReturn(mockedFailure);
            when(mockedOperatorDAO.getOperator(mockedOperatorPK)).thenReturn(mockedOperator);
            when(mockedEvent_CauseDAO.getEvent_Cause(mockedEvent_CausePK)).thenReturn(mockedEventCause);
            when(mockedUser_EquipmentDAO.getUser_Equipment(10)).thenReturn(mockedUserEquipment);
            
            
            TableRow actual = rowConverter.convert(row, e);
            Base_Data expected = new Base_Data();
              
            assertEquals(expected.getClass(), actual.getClass());
            
        }
          
          
          
      
}