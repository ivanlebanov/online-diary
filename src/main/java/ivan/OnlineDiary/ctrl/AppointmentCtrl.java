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

    /**
     * Find all appointments - calling the findAllAppointments service
     * @return list of appointments
     */
    public List<Appointment> getAllAppointments() {
        allAppointments = as.findAllAppointments();
        return allAppointments;
    }

    /**
     * Find all appointments by username - calling the service
     * @param username
     * @return list of appointments
     */
    public List<Appointment> findAppointmentsByUsername(String username) {
        userAppointments = as.findAppointmentsByUsername(username);
        return userAppointments;
    }

    /**
     * Getter for new appointment
     * @return
     */
    public Appointment getNewAppointment() {
        return newAppointment;
    }

    /**
     * Setter for new appointment
     * @param newAppointment
     */
    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }
    
    /**
     * @deprecated
     * @return set of users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * @deprecated
     * @param users set of users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Getting selected users
     * @return array of selected users in UI
     */
    public String[] getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * Setting selected users
     * @param users
     */
    public void setSelectedUsers(String[] users) {
        this.selectedUsers = users;
    }

    /**
     * Inserting appointment
     * @return string to redirect to page or null
     */
    public String insertAppointment() {
        if (as.createNewAppointment(newAppointment, selectedUsers) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    } 

    /**
     * Calling appointment Service to delete
     * @param id id to be deleted
     * @return string to redirect to page or null
     */
    public String cancel(Long id) {
        if (as.cancelAppointment(id) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }
}
