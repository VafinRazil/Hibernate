package rvafin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rvafin.converter.BirthdayConverter;
import rvafin.entity.*;
import rvafin.entity.second.Author;
import rvafin.entity.second.Book;
import rvafin.entity.second.Country;
import rvafin.entity.second.UserProfile;

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
        configuration.addAnnotatedClass(rvafin.entity.second.Userr.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(UserProfile.class);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        return configuration.buildSessionFactory();
    }
}
