package ivan.OnlineDiary.ents;

import ivan.OnlineDiary.ents.Appointment;
import ivan.OnlineDiary.ents.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-28T14:42:04")
@StaticMetamodel(User_Appointment.class)
public class User_Appointment_ { 

    public static volatile SingularAttribute<User_Appointment, Appointment> appointment;
    public static volatile SingularAttribute<User_Appointment, Long> id;
    public static volatile SingularAttribute<User_Appointment, User> user;

}