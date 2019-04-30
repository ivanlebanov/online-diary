package ivan.OnlineDiary.ents;

import ivan.OnlineDiary.ents.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-30T12:30:52")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, String> owner;
    public static volatile SingularAttribute<Appointment, Long> appointment_id;
    public static volatile SingularAttribute<Appointment, String> description;
    public static volatile SingularAttribute<Appointment, Date> startTime;
    public static volatile SingularAttribute<Appointment, Date> endTime;
    public static volatile SingularAttribute<Appointment, String> title;
    public static volatile SingularAttribute<Appointment, Date> startDate;
    public static volatile ListAttribute<Appointment, User> users;

}