package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import model.User_Equipment;
import dao.User_EquipmentDAO;

@Path("/user_Equipments")
@Stateless
@LocalBean
public class User_EquipmentWS {

    @EJB
    private User_EquipmentDAO user_EquipmentsDao;

    @GET
    @Path("/{id}")
    public User_Equipment getUser_Equipment(@PathParam("id") int id) {
        return user_EquipmentsDao.getUser_Equipment(id);
    }
    
    @POST
    public void addUser_Equipments(List<User_Equipment> user_Equipments) {
        user_EquipmentsDao.addUser_Equipments(user_Equipments);
    }
    
}