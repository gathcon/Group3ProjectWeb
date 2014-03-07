package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import model.Operator;
import model.OperatorPK;
import dao.OperatorDAO;

@Path("/operators")
@Stateless
@LocalBean
public class OperatorWS {

    @EJB
    private OperatorDAO operatorsDao;

    @GET
    @Path("/{mcc}/{mnc}")
    public Operator getOperator(
    		@PathParam("mcc") int mcc,
    		@PathParam("mnc") int mnc) {
    	
    	OperatorPK oPK = new OperatorPK(mcc, mnc);
 
        return operatorsDao.getOperator(oPK);
    }
    
    @POST
    public void addOperators(List<Operator> operators) {
        operatorsDao.addOperators(operators);
    }
}
