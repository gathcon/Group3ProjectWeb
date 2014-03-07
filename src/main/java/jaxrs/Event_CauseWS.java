package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import model.Event_Cause;
import model.Event_CausePK;
import dao.Event_CauseDAO;

@Path("/event_Causes")
@Stateless
@LocalBean
public class Event_CauseWS {

    @EJB
    private Event_CauseDAO event_CausesDao;

    @GET
    @Path("/{causeCode}/{eventId}")
    public Event_Cause getEvent_Cause(
    		@PathParam("causeCode") int causeCode,
    		@PathParam("eventId") int eventId) {
    	
    	Event_CausePK ecPK = new Event_CausePK(causeCode, eventId);
    	
        return event_CausesDao.getEvent_Cause(ecPK);
    }
    
    @POST
    public void addEvent_Causes(List<Event_Cause> event_Causes) {
        event_CausesDao.addEvent_Causes(event_Causes);
    }
    
}
