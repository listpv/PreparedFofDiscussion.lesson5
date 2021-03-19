package ru.geekbrains.task1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.geekbrains.Student;

import java.util.List;

public class StudentDao{

    private Session currentSession;

    private Transaction currentTransaction;

    public StudentDao() {
    }

    public Session openCurrentSession(SessionFactory sessionFactory) {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction(SessionFactory sessionFactory) {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void commitCurrentTransaction(){
        currentTransaction.commit();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Student entity) {
        getCurrentSession().save(entity);
    }

    public void update(Student entity) {
        getCurrentSession().update(entity);
    }

    public Student findById(Long id) {
        Student book = (Student) getCurrentSession().get(Student.class, id);
        return book;
    }

    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }

    public void delete(Long id){
        String query = String.format("DELETE FROM %s WHERE id = %s", Student.class.getName(), id);
        try {
            getCurrentSession().createQuery(query).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAll() {
        List<Student> studentList = (List<Student>) getCurrentSession().createQuery("Select s From Student s").list();
        return studentList;
    }

    public void deleteAll() {
        List<Student> entityList = findAll();
        for (Student entity : entityList) {
            delete(entity);
        }
    }





}
