/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.ents;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "diary_user")
public class User implements Serializable {

    @ManyToMany
    private List<Appointment> users;

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long user_id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String postcode;

    /**
     * Getter for user address
     * @return address string
     */
    @OneToMany(mappedBy = "user")
    
    public String getAddress() {
        return address;
    }

    /**
     * Setter for user address
     * @param address - the new value
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The user postcode
     * @return
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Setter for user postcode
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Username getter
     * @return username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
     * @param username a new username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for a password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for a password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for User's unique email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for User's email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for User's phone
     * @return user's phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for User's phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for User's first name
     * @return the name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Setter for User's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Getter for User's last name
     * @return the name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for User's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for User's Id
     * @return
     */
    public Long getId() {
        return user_id;
    }

    /**
     * Setter for User's id
     * @param user_id
     */
    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user_id != null ? user_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.user_id == null && other.user_id != null) || (this.user_id != null && !this.user_id.equals(other.user_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ivan.OnlineDiary.ents.User[ id=" + user_id + " ]";
    }
    
}
