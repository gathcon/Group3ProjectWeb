package jaxrs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Base_Data;
import dao.Base_DataDAO;

@Path("/base_Datas")
@Stateless
@LocalBean
public class Base_DataWS {

    @EJB
    private Base_DataDAO base_DatasDao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Base_Data getBase_Data(@PathParam("id") int id) {
        return base_DatasDao.getBase_Data(id);
    }
    
    @GET
    @Path("/all_Base_Datas/")
    public List<Base_Data> getBase_DataList() {
        return base_DatasDao.getAllBase_Data();
    }
    
    @GET
    @Path("/base_DataByFailureId/{failureId}")
    public List<Base_Data> getBase_DataByFailureId(@PathParam("failureId") int failureId) {
        return base_DatasDao.getBase_DataByFailureId(failureId);
    }
    
    @GET
    @Path("/base_DataByDateRange/{startDateString}/{endDateString}")
    public List<Base_Data> getBase_DataByDateRange(@PathParam("startDateString") String startDateString,
    		@PathParam("endDateString") String endDateString) throws ParseException {
    	//String startDateString = "2013-01-11 17:15:00.0";
    	Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateString);
    	//String endDateString = "2013-01-11 17:22:00.0";
    	Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateString);
        return base_DatasDao.getBase_DataByDateRange(startDate, endDate);
    }
    
    @POST
    public void addBase_Datas(List<Base_Data> base_Datas) {
        base_DatasDao.addBase_Datas(base_Datas);
    }
    
}
