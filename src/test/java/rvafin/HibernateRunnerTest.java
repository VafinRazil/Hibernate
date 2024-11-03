package rvafin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import rvafin.entity.*;
import rvafin.entity.second.Author;
import rvafin.entity.second.Book;
import rvafin.util.HibernateUtil;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void oneToMany2(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            EntityGraph entityGraph = session.getEntityGraph("removeBooks");
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence.fetchgraph", entityGraph);
            Author author = session.find(Author.class, 2L, properties);
            session.remove(author);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkOneToOne2(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            rvafin.entity.second.Userr user = session.get(rvafin.entity.second.Userr.class, 4L);
            System.out.println(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkInheritance(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List users = session.createQuery("""
                        select u from User u
                        where u.personalInfo.firstname='Pavel'
                        """).list();
            System.out.println(users);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkH2(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Company company = Company.builder()
                   .name("VK").build();
            session.saveOrUpdate(company);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void thirdHomework(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            TrainerCourses trainerCourses1 = TrainerCourses.builder().build();
            TrainerCourses trainerCourses2 = TrainerCourses.builder().build();
            Trainer trainer = Trainer.builder()
                    .name("Oleja")
                    .build();
            session.saveOrUpdate(trainer);
            Course course1 = session.get(Course.class, 5L);
            Course course2 = session.get(Course.class, 2L);
            trainerCourses1.setTrainer(trainer);
            trainerCourses2.setTrainer(trainer);
            trainerCourses1.setCourse(course1);
            trainerCourses2.setCourse(course2);
            session.saveOrUpdate(trainerCourses1);
            session.saveOrUpdate(trainerCourses2);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void secondHomework(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, 2L);
            course.removeStudent();
            session.remove(course);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void firstHomework(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, 1L);
            List<Student> students = course.getStudents();
            for (Student student : students) {
                if (student.getStudentProfile().getAvgRating() < 6){
                    session.delete(student);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkManyToMany(){
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, 3L);
            Chat chat = session.get(Chat.class, 1L);
            UserChat userChat = new UserChat();
            userChat.setCreatedAt(Instant.now());
            userChat.setCreatedBy("Oleg");
            userChat.setUser(user);
            userChat.setChat(chat);
            session.saveOrUpdate(userChat);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void checkOneToOne(){
        User user = User.builder()
                .username("razil4@mail.ru")
                .build();
        Profile profile = Profile.builder()
                .lang("RU")
                .street("Ostrovsckogo1")
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