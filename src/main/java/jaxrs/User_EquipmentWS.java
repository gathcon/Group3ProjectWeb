package jaxrs;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

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
    @GET
    @Path("/jsp/{id}")
    public void getUser_Equipment_withJSP(@PathParam("id") int id) throws IOException {
      	HttpServletRequest request = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
      	User_Equipment userequipment = user_EquipmentsDao.getUser_Equipment(id);
        HttpServletResponse response = ResteasyProviderFactory.getContextData(HttpServletResponse.class);
        HttpSession session = request.getSession();
        session.setAttribute("userequipment", userequipment);
        response.sendRedirect("/Group3ProjectWeb/printuserequipment.jsp");        
    }
    
    @GET
    @Path("/all_User_Equipments/")
    public List<User_Equipment> getOperatorList() {
        return user_EquipmentsDao.getAllUser_Equipments();
    }
    
    @POST
    public void addUser_Equipments(List<User_Equipment> user_Equipments) {
        user_EquipmentsDao.addUser_Equipments(user_Equipments);
    }
    
    @GET
    @Path("/return/")
    public void userEquipmentReturnPage() throws IOException {
    	System.out.println("return in the group project! ! ! ");
      	HttpServletRequest request = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
        HttpServletResponse response = ResteasyProviderFactory.getContextData(HttpServletResponse.class);
        response.sendRedirect("/Group3ProjectWeb/return.jsp");
    }
    
}