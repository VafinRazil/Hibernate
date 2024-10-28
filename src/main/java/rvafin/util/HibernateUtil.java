package rvafin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rvafin.converter.BirthdayConverter;
import rvafin.entity.Company;
import rvafin.entity.Profile;
import rvafin.entity.User;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        return configuration.buildSessionFactory();
    }
}
