package jaxrs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import reader.Loader;
 
@Path("/file")
@Stateless
@LocalBean
public class DataLoaderWS {
 
	@EJB
	private Loader loader;
	
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm FileUploadForm form) {
 
		
		String fileName = "/home/uploadedData.xls";
 
		try {
			writeFile(form.getData(), fileName);
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
		System.out.println("Done");
 
		//For now data is uploaded onto local computer in the home directory, 
		//Need to complete troubleshooting the loader class issues
		//loader.loadFile(fileName);
		
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
 
	}
 
	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {
 
		File file = new File(filename);
 
		if (!file.exists()) {
			file.createNewFile();
		}
 
		FileOutputStream fop = new FileOutputStream(file);
 
		fop.write(content);
		fop.flush();
		fop.close();
 
	}
}
