package rvafin;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import rvafin.entity.Birthday;
import rvafin.entity.PersonalInfo;
import rvafin.entity.Role;
import rvafin.entity.User;
import rvafin.util.HibernateUtil;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivan1@mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ivan")
                        .lastname("Rva")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
                        .build())
                .role(Role.ADMIN)
                .build();
        try {SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace(System.out);
        }
    }
}
