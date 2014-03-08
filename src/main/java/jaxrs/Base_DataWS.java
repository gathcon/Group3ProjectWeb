package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    
    @POST
    public void addBase_Datas(List<Base_Data> base_Datas) {
        base_DatasDao.addBase_Datas(base_Datas);
    }
    
}
