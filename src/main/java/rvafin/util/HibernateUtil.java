package rvafin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rvafin.converter.BirthdayConverter;
import rvafin.entity.User;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        return configuration.buildSessionFactory();
    }
}
