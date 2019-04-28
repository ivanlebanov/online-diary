/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.ctrl;

import ivan.OnlineDiary.bus.UserService;
import ivan.OnlineDiary.ents.User;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ivan
 */
@Named(value = "userCtrl")
@RequestScoped
public class UserCtrl {

    /**
     * Creates a new instance of UserCtrl
     */
    public UserCtrl() {
    }

    private User newUser = new User();
    private List<User> allUsers;
    private String confirm;

    /**
     * Getter for confirmation string
     * @return
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * Setter for confirmation string
     * @param confirm
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * Getter for a newly created user coming from the user interface
     * @return a user object
     */
    public User getNewUser() {
        return newUser;
    }

    /**
     * Setter used for the creating of new user
     * @param newUser user object
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    @EJB
    private UserService us;

    /**
     * Call service for all users
     * @return
     */
    public List<User> getAllUsers() {
        allUsers = us.findAllUsers();
        return allUsers;
    }

    /**
     * Get id by username
     * @param username
     * @return an Id of the user
     */
    public Long getIdByUser(String username) {
        return us.findUserIDByUsername(username).getId();
    }
    
    /**
     * Add a new user. Used on user page
     * @return string - the page to be redirected to
     */
    public String insertUser() {
        if (us.createNewUser(newUser, confirm) == true) {
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * Login a user using the service
     * If unsuccessful  an error message would be shown
     * @return string - the page to be redirected to
     */
    public String loginUser() {
        User user = us.validateLogin(newUser.getUsername(), newUser.getPassword());
        FacesContext context = FacesContext.getCurrentInstance();
        if (user != null) {
            // add user
            context.getExternalContext().getSessionMap().put("user", user);
            return "index.xhtml?faces-redirect=true";
        } else {
            context.addMessage("loginID:logbttn", new FacesMessage("Wrong username or password"));
            return null;
        }
    }

    /**
     * Invalidate user session and redirect to login page
     * @return string - the page to be redirected to
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

}
