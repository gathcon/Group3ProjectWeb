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
	@Path("/all_Base_Datas/")
	public List<Base_Data> getAllBase_Data() {
		return base_DatasDao.getAllBase_Data();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/IMSIByFailureId/{failureId}")
	public List<Base_Data> getIMSIByFailureId(
			@PathParam("failureId") int failureId) {
		return base_DatasDao.getIMSIByFailureId(failureId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eventID_CauseCodeByImsi/{imsi}")
	public List<Event_Cause> getEventID_CauseCodeByImsi(
			@PathParam("imsi") BigInteger imsi) {
		return base_DatasDao.getEventID_CauseCodeByImsi(imsi);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uniqueCauseCodeByImsi/{imsi}")
	public List<Object[]> getUniqueCauseCodeByImsi(
			@PathParam("imsi") BigInteger imsi) {
		return base_DatasDao.getUniqueCauseCodesByImsi(imsi);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/IMSIByDateRange/{startDateString}/{endDateString}")
	public List<Base_Data> getIMSIByDateRange(
			@PathParam("startDateString") String startDateString,
			@PathParam("endDateString") String endDateString)
			throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);
		return base_DatasDao.getIMSIByDateRange(startDate, endDate);
	}

	@GET
	@Path("/allImsis/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Base_Data> getAllImsis() {
		return base_DatasDao.getImsisForDropdown();
	}
	
	@GET
	@Path("/allPhoneModels/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Base_Data> getAllPhoneModels() {
		return base_DatasDao.getAllPhoneModels();
	}

	@GET
	@Path("/FailureCountByModelAndByDateRange/{model}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Number getFailureCountByModelAndByDateRange(
			@PathParam("model") String model,
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString) throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);
		return base_DatasDao.failureCountByModelBetweenDates(startDate,
				endDate, model);
	}

	@GET
	@Path("/TotalFailuresDurationForIMSIs/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> checkForIMSIFailuresDurationBetweenDates(
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString) throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);

		return base_DatasDao.totalFailuresDurationForImsiBetweenDates(startDate,
				endDate);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/eventID_CauseCodeByModel/{model}")
	public List<Object[]> getEventID_CauseCodeByModel(
			@PathParam("model") String model) {
		return base_DatasDao.getEventID_CauseCodeByModel(model);
	}

	@GET
	@Path("/imsifailure/{imsi}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Number checkForAnImsisFailuresBetweenDates(
			@PathParam("imsi") String imsi,
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString) throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);

		BigInteger imsibigint = BigInteger.valueOf(Long.parseLong(imsi));
		return base_DatasDao.imsiFailureCountBetweenDates(startDate, endDate,
				imsibigint);
	}

	@GET
	@Path("/top10ImsiFailureCount/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> getTop10ImsiFailureCountBetweenDates(
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString) throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);
		return base_DatasDao.getTop10ImsisByDate(startDate, endDate);
	}
	
	@GET
	@Path("/top10CombinationsByDate/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> getTop10CombinationsBetweenDates(
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString) throws ParseException {

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.S");

		Date startDateIn = inFormat.parse(startDateString);
		String startDateOut = outFormat.format(startDateIn);

		Date endDateIn = inFormat.parse(endDateString);
		String endDateOut = outFormat.format(endDateIn);

		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(startDateOut);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.parse(endDateOut);
		return base_DatasDao.getTop10CombinationsByDate(startDate, endDate);
	}
	
	@GET
	@Path("/allImsiS/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BigInteger> getAllImsiS() {
		return base_DatasDao.getAllImsis();
	}
}
