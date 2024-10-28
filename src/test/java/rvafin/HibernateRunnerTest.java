package rvafin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import rvafin.entity.*;
import rvafin.util.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunnerTest {

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        //User user = User.builder()
        //        .username("ivan5@mail.ru")
        //        .firstname("Ivan")
        //        .lastname("Ivanov")
        //        .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
        //        .build();
        //String sql = """
        //        insert into
        //        %s
        //        (%s)
        //        values
        //        (%s)
        //        """;
//
        //String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
        //        .map(table -> table.schema() + "." + table.name())
        //        .orElse(user.getClass().getName());
//
        //Field[] fields = user.getClass().getDeclaredFields();
        //String columnNames = Arrays.stream(fields)
        //        .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
        //                .map(Column::name)
        //                .orElse(field.getName())
        //        ).collect(Collectors.joining(","));
//
        //String columnValues =  Arrays.stream(fields)
        //        .map(field -> "?")
        //        .collect(Collectors.joining(","));
//
        //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        //PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
        //for (int i = 0; i < fields.length; i++){
        //    fields[i].setAccessible(true);//доступ к private полям
        //    preparedStatement.setObject(i + 1, fields[i].get(user));
        //}
        //System.out.println(preparedStatement);
        //preparedStatement.executeUpdate();
//
        //preparedStatement.close();
        //connection.close();
    }

    @Test
    public void checkOneToOne(){
        User user = User.builder()
                .username("razil3@mail.ru")
                .build();
        Profile profile = Profile.builder()
                .lang("RU")
                .street("Ostrovsckogo")
                .build();
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            profile.setUser(user);
            session.saveOrUpdate(profile);
            //работа ведется с объектом в кеше
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkOrphalRemoval(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Company company = session.get(Company.class, 2);
            company.getUsers().removeIf((user) -> user.getId().equals(5L));
            //работа ведется с объектом в кеше
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addNewUserAndCompany(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Company company = Company.builder()
                    .name("Mail")
                    .build();
            User user = User.builder()
                    .username("ivan7@mail.ru")
                    .build();
            company.addUser(user);
            session.save(company);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkOneToMany(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Company company = session.get(Company.class, 2);
            System.out.println(company.getUsers());
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}