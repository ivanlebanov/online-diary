/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.bus;

import ivan.OnlineDiary.ents.User;
import ivan.OnlineDiary.pers.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ivan
 */
@Stateless
public class UserService {

    @EJB
    private UserFacade uf;

    /** 
    * List all appointments for a user
    * 
    * This method is unfinished.
    * @param u username object
    * @param confirm confirmation string
    * @return a list of appointments
    */
    public Boolean createNewUser(User u, String confirm) {
        Boolean passwordsMatch = checkPasswords(u, confirm);
        Boolean userExists = checkDuplicates(u);
        if (userExists == false && passwordsMatch == true) {
            uf.create(u);
            return true;
        } else {
            return false;
        }
    }
    /** 
    * Duplicate User check
    * 
    * This method validates whether the user has inputed unique data.
    * Returns error messages if it is the case.
    * @param u username object
    * @return a list of appointments
    */
    public Boolean checkDuplicates(User u) {
        Boolean userExists = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            userExists = false;
        }
        if (uf.findUsersByUsername(u.getUsername()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:username", new FacesMessage("Username already exists"));
        }
        if (uf.findUsersByEmail(u.getEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:email", new FacesMessage("Email already exists"));
        }
        return userExists;
    }
    /** 
    * Check Password
    * 
    * This method validates whether the user has inputed unique data.
    * @param u user object
    * @param confirm confirmation string validation
    * @return a list of appointments
    */
    public Boolean checkPasswords(User u, String confirm) {
        Boolean passwordsMatch = false;
        if (u.getPassword().equals(confirm)) {
            passwordsMatch = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("createID:confirm", new FacesMessage("Passwords do not match"));
        }
        return passwordsMatch;
    }

    /** 
    * Login validation
    * 
    * This method attempts to call the User Facade for a user with the inputed username and password.
    * @param username username
    * @param password string representation of the password as saved in the database
    * @return a list of appointments
    */
    public User validateLogin(String username, String password) {
        User user = uf.findUserByCredentials(username, password);
        if (user == null) {
            return user;
        }
        return user;
    }
    /** 
    * List all users
    * @return a list of users
    */
    public List<User> findAllUsers() {
        return uf.findAll();
    }
    /** 
    * Find a user Id by username
     * @param username username of the user
    * @return a user object
    */
    public User findUserIDByUsername(String username) {
        return uf.findUserIDByUsername(username);
    }
}
