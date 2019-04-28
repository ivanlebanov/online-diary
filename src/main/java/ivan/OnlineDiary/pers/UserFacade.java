/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.pers;

import ivan.OnlineDiary.ents.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ivan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "com.mycompany_OnlineDiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * A constructor of the User facade
     */
    public UserFacade() {
        super(User.class);
    }

    /**
     * A method to fetch user by username from the database
     * @param username - unique username
     * @return User object or null
     */
    public User findUsersByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            return query.setParameter("username", username).getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
        
    /**
     * A method to fetch user by given credentials from the database
     * @param username - unique username
     * @param password - password string
     * @return User object or null
     */
    public User findUserByCredentials(String username, String password) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE (u.username = :username AND u.password = :password)", User.class);
            return (User)query.setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * A method to fetch user by given email from the database.
     * Used mostly internally for validation purpose.
     * @param email - email of the user
     * @return User object or null
     */
    public User findUsersByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            return query.setParameter("email", email).getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Fetch user by given username from the database.
     * @param username - unique username
     * @return User object or null
     */
    public User findUserIDByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u.user_id FROM User u WHERE u.username = :username", User.class);
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

}
