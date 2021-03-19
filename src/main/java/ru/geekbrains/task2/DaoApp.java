package ru.geekbrains.task2;

import org.hibernate.SessionFactory;
import ru.geekbrains.Person;
import ru.geekbrains.SessionCreater;
import ru.geekbrains.Student;

public class DaoApp {

    public static void main(String[] args) {

        SessionFactory factory = SessionCreater.getSessionFactory();
        try{
            baseStudent(factory);
            basePerson(factory);
        }finally {
            factory.close();
        }

    }

    public static void baseStudent(SessionFactory sessionFactory){

        BaseDao<Student> studentBaseDao = new BaseDao<>(new Student());

        try{
            studentBaseDao.openCurrentSessionwithTransaction(sessionFactory);
            for(int i = 1; i <= 1000; i++){
                Student student = new Student();
                student.setName("Student_" + i);
                if(i < 300){
                    student.setMark(3);
                }else if(i < 700){
                    student.setMark(4);
                }else {
                    student.setMark(5);
                }
                studentBaseDao.persist(student);
            }
            Student student = studentBaseDao.findById(10L);
            System.out.println("Из таблицы -- " + student);
            student.setMark(10);
            studentBaseDao.update(student);
            System.out.println("После изменений -- " + student);
            System.out.println("Количество -- " +studentBaseDao.findAll().size());
            System.out.println(studentBaseDao.findById(20L));
            studentBaseDao.delete(student);
            System.out.println("Количество -- " + studentBaseDao.findAll().size());
            studentBaseDao.delete(20L);
            System.out.println("Количество -- " + studentBaseDao.findAll().size());
            studentBaseDao.deleteAll();
            System.out.println(studentBaseDao.findAll());
            System.out.println("Количество -- " + studentBaseDao.findAll().size());
            studentBaseDao.commitCurrentTransaction();
        }
        finally {
            if (studentBaseDao.getCurrentSession() != null) {
                studentBaseDao.closeCurrentSession();
            }
        }
    }

    public static void basePerson(SessionFactory sessionFactory){

        BaseDao<Person> personBaseDao = new BaseDao<>(new Person());

        try {
            personBaseDao.openCurrentSessionwithTransaction(sessionFactory);

            for(int i = 1; i <= 100; i++){
                personBaseDao.persist(new Person("Person_" + i));
            }
            Person person = personBaseDao.findById(10L);
            System.out.println("Из таблицы -- " + person);
            person.setName("Bob");
            personBaseDao.update(person);
            System.out.println("После изменений -- " + person);
            System.out.println("Количество -- " +personBaseDao.findAll().size());
            System.out.println(personBaseDao.findById(20L));
            personBaseDao.delete(person);
            System.out.println("Количество -- " + personBaseDao.findAll().size());
            personBaseDao.delete(20L);
            System.out.println("Количество -- " + personBaseDao.findAll().size());
            personBaseDao.deleteAll();
            System.out.println(personBaseDao.findAll());
            System.out.println("Количество -- " + personBaseDao.findAll().size());
            personBaseDao.commitCurrentTransaction();
        }
        finally {
            if (personBaseDao.getCurrentSession() != null) {
                personBaseDao.closeCurrentSession();
            }
        }
    }
}
