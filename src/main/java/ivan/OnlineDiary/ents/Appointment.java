/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.ents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.JoinFetch;

/**
 *
 * @author Ivan
 */
@Entity
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointment_id;
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;
    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;
    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;
    @JoinFetch
    @ManyToMany
    private List<User> users;
    private String title;
    private String description;
    private String owner;
    
    /**
     * An appointment entity representing an event with one of many users
     * 
     */
    public Appointment() {
        this.users = new ArrayList<>();
    }

    /**
     * Getter for all users of the appointment
     * @return list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the users of an appointment
     * @param users list of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Getter for a title
     * @return the title of an appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for a title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description
     * Not currently used in the UI
     * @return the description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
    * Getter for description
    * Not currently used in the UI
    * @param description the new description of the appointment
    */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of the owner and creator of an appointment
     * @return Owner and creator of an event - user's username
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter of the owner and creator of an appointment
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Appointments don't have an endDate.
     * Getter of starting date.
     * @return date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter of starting date.
     * @param startDate - a new date to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for startTime - DATE format
     * @return the date
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter for startTime - DATE format
     * @param startTime newly set start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for endTime - DATE format
     * @return the date
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter for endTime - DATE format
     * @param endTime a new date for end date
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return the appointment id
     */
    public Long getId() {
        return appointment_id;
    }

    /**
     * Setter for appointment id
     * @param appointment_id
     */
    public void setId(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointment_id != null ? appointment_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointment_id == null && other.appointment_id != null) || (this.appointment_id != null && !this.appointment_id.equals(other.appointment_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ivan.OnlineDiary.ents.Appointment[ id=" + appointment_id + " ]";
    }

}
