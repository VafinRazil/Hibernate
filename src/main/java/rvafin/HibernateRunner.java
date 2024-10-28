package rvafin;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import rvafin.entity.*;
import rvafin.util.HibernateUtil;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivan5@mail.ru")
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
            Company company = session.get(Company.class, 2);
            user.setCompany(company);
            session.saveOrUpdate(company);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace(System.out);
        }
    }
}
