package jaxrs;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eventID_CauseCodeByImsi/{imsi}")
    public List<Integer> getEventID_CauseCodeByImsi(@PathParam("imsi") BigInteger imsi) {
        return base_DatasDao.getEventID_CauseCodeByImsi(imsi);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/IMSIByDateRange/{startDateString}/{endDateString}")
    public List<Base_Data> getIMSIByDateRange(@PathParam("startDateString") String startDateString,
    		@PathParam("endDateString") String endDateString) throws ParseException {
    	
    	Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateString);
    	Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateString);
        return base_DatasDao.getIMSIByDateRange(startDate, endDate);
    }
    
    @POST
    public void addBase_Datas(List<Base_Data> base_Datas) {
        base_DatasDao.addBase_Datas(base_Datas);
    }
    
    @POST
    @Path("/json")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
   // @Produces(MediaType.APPLICATION_JSON)
    public void getDateTime(@FormParam("datetimepicker") String dateTime) {
       System.out.println("JSON date " + dateTime);
    }
    
    @POST
    @Path("/imsifailure")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Number checkForImsiFailuresBetweenDates(@FormParam("startDate") String startDate, @FormParam("endDate") String endDate, @FormParam("imsi") String imsi) throws ParseException {
    	//System.out.println("Hello " + startDate + " " + endDate + " " + imsi);
    	Date startD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
    	//String endDateString = "2013-01-11 17:22:00.0";
    	Date endD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);   	
    	
    	BigInteger imsibigint = BigInteger.valueOf(Long.parseLong(imsi));
    	return base_DatasDao.imsiFailureCountBetweenDates(startD, endD, imsibigint);
    }
    
}
