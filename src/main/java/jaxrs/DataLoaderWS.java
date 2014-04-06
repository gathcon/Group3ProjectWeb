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

import loader.FileUploadForm;
import loader.Loader;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
 
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
		
		long start = System.currentTimeMillis();
		
		String fileName = "/home/uploadedData.xls";
		
		try {
			writeFile(form.getData(), fileName);
		} catch (IOException e) {
 
			e.printStackTrace();
		}

		loader.loadFile(fileName);
		
		long time = System.currentTimeMillis() - start;
		
		return Response.status(200)
				.entity("<!DOCTYPE html><html><head><meta charset='UTF-8'>"
						+ "<title>File Uploaded</title>"
						+ "<link rel='stylesheet' type='text/css' href='../../style.css'>"
						+ "<link href='http://fonts.googleapis.com/css?family=Maven+Pro:400,500' rel='stylesheet' type='text/css'>"
						+ "</head>"
						+ "<body>"
						+ "<div id='logo'><img src='../../Ericsson.png'></div>"
						+ "<div id='heading'><h1>File Uploaded Successfully</h1></div>"
						+ "<div id='menu'><ul></ul></div>"
						+ "<div id='mainbody'>"
						+ "<form action='../../sysAdminHome.html'><input type='submit' value='Continue' size='20' /></form>"
						+ "<p>Time taken: "
						+ time
						+ " ms</p>"
						+ "</div>"
						+ "</body>"
						+ "</html>").build();

	}
 
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
