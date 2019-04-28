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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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

    public Boolean checkPasswords(User u, String confirm) {
        Boolean passwordsMatch = false;
        if (u.getPassword().equals(confirm)) {
            passwordsMatch = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("createID:confirm", new FacesMessage("Passwords do not match"));
        }
        return passwordsMatch;
    }

    public User validateLogin(String username, String password) {
        User user = uf.findUserByCredentials(username, password);
        if (user == null) {
            return user;
        }
        return user;
    }

    public List<User> findAllUsers() {
        return uf.findAll();
    }
    public User findUserIDByUsername(String username) {
        return uf.findUserIDByUsername(username);
    }
}
