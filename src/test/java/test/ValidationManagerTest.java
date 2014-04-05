package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.EJB;

import loader.ValidationManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ValidationManagerTest {

	@Deployment
	public static WebArchive createTestArchive() {
		MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "dao", "jaxrs", "model", "restApp", "loader")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(resolver.artifact("org.mockito:mockito-all:1.9.5").resolveAsFiles())
				.addAsLibraries(resolver.artifact("org.apache.poi:poi").resolveAsFiles());
	}
	
	@EJB
	ValidationManager validationManager;
	
	HSSFWorkbook workbook = null;
	
	@Test
	public void setCellToNullTest() {
		try {
			workbook = new HSSFWorkbook(new FileInputStream("/home/datasets/TestDataSample.xls"));
		} catch (FileNotFoundException e) {
			fail("File not found");
		} catch (IOException e) {
			fail("IO Exception");
		}
		HSSFCell cell = workbook.getSheetAt(0).getRow(4).getCell(2);
				
		validationManager.setCellToNull(cell);

		assertEquals(Cell.CELL_TYPE_BLANK, workbook.getSheetAt(0).getRow(4).getCell(2).getCellType());
	}
	
	@Test 
	public void isCellInvalidTest() {
		try {
			workbook = new HSSFWorkbook(new FileInputStream("/home/datasets/TestDataSample.xls"));
		} catch (FileNotFoundException e) {
			fail("File not found");
		} catch (IOException e) {
			fail("IO Exception");
		}
		HSSFCell cell;
		// numeric cell that is numeric
		cell = workbook.getSheetAt(0).getRow(1).getCell(2);
		assertEquals(false, validationManager.isCellInvalid(cell, "Failure Class"));
		
		// numeric cell that is string
		cell = workbook.getSheetAt(0).getRow(3).getCell(2);
		assertEquals(true, validationManager.isCellInvalid(cell, "Failure Class"));
		
		// numeric cell that is null
		cell = workbook.getSheetAt(0).getRow(4).getCell(2);
		assertEquals(false, validationManager.isCellInvalid(cell, "Failure Class"));

		// string cell that is string
		cell = workbook.getSheetAt(0).getRow(1).getCell(9);
		assertEquals(false, validationManager.isCellInvalid(cell, "NE Version"));
		
		// string cell that is numeric
		cell = workbook.getSheetAt(0).getRow(10).getCell(9);
		assertEquals(true, validationManager.isCellInvalid(cell, "NE Version"));
	}
	
	@Test
	public void testCellValidation() {
		try {
			workbook = new HSSFWorkbook(new FileInputStream("/home/datasets/TestDataSample.xls"));
		} catch (FileNotFoundException e) {
			fail("File not found");
		} catch (IOException e) {
			fail("IO Exception");
		}
		validationManager.validate(workbook);
		
		assertEquals(2, workbook.getSheetAt(0).getPhysicalNumberOfRows());
		
		for(Row row : workbook.getSheetAt(0)) {
			if(row.getRowNum() != 0) {
				
				assertEquals("Cell type doesn't match.", Cell.CELL_TYPE_NUMERIC, row.getCell(2).getCellType());
				assertEquals("Cell type doesn't match.", Cell.CELL_TYPE_STRING, row.getCell(9).getCellType());
			}
		}
	}
}
