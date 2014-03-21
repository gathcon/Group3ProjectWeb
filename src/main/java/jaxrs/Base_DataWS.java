package jaxrs;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Base_Data;
import model.Event_Cause;
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
    public List<Base_Data> getAllBase_Data() {
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
    public List<Event_Cause> getEventID_CauseCodeByImsi(@PathParam("imsi") BigInteger imsi) {
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
    
    @GET
	@Path("/TotalFailuresDurationForIMSIs/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Base_Data> checkForIMSIFailuresDurationBetweenDates(@PathParam("startDate") String startDate,
		@PathParam("endDate") String endDate) throws ParseException {

		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);   	

		return base_DatasDao.totalFailuresDurationForImsiBetweenDates(start, end);
	}
    
    @GET
    @Path("/imsifailure/{imsi}/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Number checkForAnImsisFailuresBetweenDates(@PathParam("imsi") String imsi,@PathParam("startDate") String startDate,
    		@PathParam("endDate") String endDate) throws ParseException {
    		
    	System.out.println(" imsi " + imsi + " start " + startDate + "end " + endDate);
    	
    	Date startD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
    	Date endD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);   	
    	
    	BigInteger imsibigint = BigInteger.valueOf(Long.parseLong(imsi));
    	return base_DatasDao.imsiFailureCountBetweenDates(startD, endD, imsibigint);
    }
    
}
