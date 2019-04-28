/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivan.OnlineDiary.pers;

import ivan.OnlineDiary.ents.Appointment;
import ivan.OnlineDiary.ents.User;
import java.util.List;
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
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "com.mycompany_OnlineDiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
 
    public List<Appointment> findAppointmentsByUsername(String username, User user) {
        try {
            TypedQuery<Appointment> query = em.createQuery(
                    "SELECT DISTINCT(a) FROM Appointment a WHERE a.users = :user ORDER BY a.startDate", Appointment.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch(NoResultException e){
            return null;
        }
    }
}
