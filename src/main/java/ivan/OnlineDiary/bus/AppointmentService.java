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
  /** 
  * Create an appointment
  * 
  * This method creates a new appointment.
  * N.B. clash between users has not been implemented
  * @param a send a valid appointment object
  * @param users - an array of all users selected as participants
  * @return always true
  */
    public Boolean createNewAppointment(Appointment a, String[] users) {
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
   /** 
  * Delete an appointment
  * 
  * This method deletes an appointment. To implement validations in the future.
  * @param id a valid appointment id
  * @return true after successful deletion
  */
    public Boolean cancelAppointment(Long id) {
        Appointment a = af.find(id);
        af.remove(a);
        return true;
    }
  /** 
  * Check for clashes
  * 
  * This method is unfinished.
  * @param a a valid appointment object
  * @param u user to be checked
  * @return true if there is a clash
  */
    public Boolean checkClash(Appointment a, User u) {
        Boolean appointmentClash = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            appointmentClash = false;
        }

        return appointmentClash;
    }
  /** 
  * List all appointments
  * @return a list of appointments
  */
    public List<Appointment> findAllAppointments() {
        return af.findAll();
    }
  /** 
  * List all appointments for a user
  * @param username unique username of the user
  * @return a list of appointments
  */
    public List<Appointment> findAppointmentsByUsername(String username) {
        return af.findAppointmentsByUsername(username, uf.findUsersByUsername(username));
    }
}
