/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.bus;

import ivan.OnlineDiary.ents.Appointment;
import ivan.OnlineDiary.ents.User;
import ivan.OnlineDiary.pers.AppointmentFacade;
import ivan.OnlineDiary.pers.UserFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ivan
 */
@Stateless
public class AppointmentService {
    
    @EJB
    private AppointmentFacade af;
    @EJB
    private UserFacade uf;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Boolean createNewAppointment(Appointment a, String[] users) {
        // Set the owner of appointment to current logged in user 
        FacesContext context = FacesContext.getCurrentInstance();
        User owner = (User) context.getExternalContext().getSessionMap().get("user");
        String username = owner.getUsername();
        a.setOwner(username);
        List<User> userList = new ArrayList<User>();
        for (String user : users) {
            userList.add(uf.findUsersByUsername(user));
        }
        a.setUsers(userList);
        af.create(a);
        return true;
    }
    public Boolean cancelAppointment(Long id) {
        // Set the owner of appointment to current logged in user 
        FacesContext context = FacesContext.getCurrentInstance();
        User owner = (User) context.getExternalContext().getSessionMap().get("user");
        String username = owner.getUsername();
        Appointment a = new Appointment();
        a = af.find(id);
        af.remove(a);
        return true;
    }
    
    public Boolean checkClash(Appointment a, User u) {
        Boolean appointmentClash = false;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            appointmentClash = false;
        }
        if (uf.findUsersByUsername(u.getUsername()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:username", new FacesMessage("Username already exists"));
        }
        if (uf.findUsersByEmail(u.getEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:email", new FacesMessage("Email already exists"));
        }
        return appointmentClash;
    }

    public List<Appointment> findAllAppointments() {
        return af.findAll();
    }
    public List<Appointment> findAppointmentsByUsername(String username) {
        return af.findAppointmentsByUsername(username, uf.findUsersByUsername(username));
    }
}
