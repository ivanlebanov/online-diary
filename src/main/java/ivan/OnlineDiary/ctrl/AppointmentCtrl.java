/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.ctrl;

import ivan.OnlineDiary.bus.AppointmentService;
import ivan.OnlineDiary.ents.Appointment;
import ivan.OnlineDiary.ents.User;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ivan
 */
@Named(value = "appointmentCtrl")
@RequestScoped
public class AppointmentCtrl {

  

    /**
     * Creates a new instance of AppointmentCtrl
     */
    public AppointmentCtrl() {
    }
    
    private Appointment newAppointment = new Appointment();
    private Set<User> users;
    private String[] selectedUsers;
    private List<Appointment> allAppointments;
    private List<Appointment> userAppointments;
    @EJB
    private AppointmentService as;

    public List<Appointment> getAllAppointments() {
        allAppointments = as.findAllAppointments();
        return allAppointments;
    }
    public List<Appointment> findAppointmentsByUsername(String username) {
        userAppointments = as.findAppointmentsByUsername(username);
        return userAppointments;
    }
    public Appointment getNewAppointment() {
        return newAppointment;
    }

    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }
    
     public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public String[] getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(String[] users) {
        this.selectedUsers = users;
    }
    public String insertAppointment() {
        if (as.createNewAppointment(newAppointment, selectedUsers) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    } 
    public String cancel(Long id) {
        if (as.cancelAppointment(id) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }
}
