package ru.geekbrains.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class BaseDao <T>{

    private T typeParameterClass;

    private Session currentSession;
    private Transaction currentTransaction;

    public BaseDao() {
    }

    public BaseDao(T typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
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

    public void persist(T entity) {
        getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public T findById(Long id) {
        T object = (T) getCurrentSession().get(typeParameterClass.getClass(), id);
        return object;
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void delete(Long id)  {
        String query = String.format("DELETE FROM %s WHERE id = %s", typeParameterClass.getClass().getName(), id);
        try {
            getCurrentSession().createQuery(query).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> findAll() {
        String query = String.format("SELECT obj FROM %s obj", typeParameterClass.getClass().getName());
        List<T> books = (List<T>) getCurrentSession().createQuery(query).list();
        return books;
    }

    public void deleteAll() {
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }
}
