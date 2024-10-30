package rvafin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rvafin.converter.BirthdayConverter;
import rvafin.entity.*;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAnnotatedClass(Chat.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(UserChat.class);
        configuration.addAnnotatedClass(StudentProfile.class);
        configuration.addAnnotatedClass(Trainer.class);
        configuration.addAnnotatedClass(TrainerCourses.class);
        configuration.addAnnotatedClass(Programmer.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        return configuration.buildSessionFactory();
    }
}
