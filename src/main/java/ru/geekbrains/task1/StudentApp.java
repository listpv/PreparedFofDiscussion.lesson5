package ru.geekbrains.task1;

import org.hibernate.SessionFactory;
import ru.geekbrains.SessionCreater;
import ru.geekbrains.Student;

public class StudentApp {

    public static void main(String[] args) {

        SessionFactory factory = SessionCreater.getSessionFactory();

        StudentDao studentDao = new StudentDao();

        try{
            studentDao.openCurrentSessionwithTransaction(factory);
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
                studentDao.persist(student);
            }
            Student student = studentDao.findById(10L);
            System.out.println("Из таблицы -- " + student);
            student.setMark(10);
            studentDao.update(student);
            System.out.println("После изменений -- " + student);
            System.out.println("Количество -- " + studentDao.findAll().size());
            studentDao.delete(student);
            System.out.println("Количество -- " + studentDao.findAll().size());
            studentDao.delete(20L);
            System.out.println("Количество -- " + studentDao.findAll().size());
            studentDao.deleteAll();
            System.out.println(studentDao.findAll());
            System.out.println("Количество -- " + studentDao.findAll().size());
            System.out.println(Student.class.getName());
            studentDao.commitCurrentTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            factory.close();
            if (studentDao.getCurrentSession() != null) {
                studentDao.closeCurrentSession();
            }
        }

    }
}
