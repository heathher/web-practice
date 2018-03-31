package dao.implementation;

import dao.interfaces.EmployeeInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.EmployeeEntity;
import java.util.List;

public class EmployeeImplementation implements EmployeeInterface{
    public void save(EmployeeEntity employee) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    public void update(EmployeeEntity employee) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
    }
    public void delete(EmployeeEntity employee) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
    }
    public EmployeeEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        EmployeeEntity employee = (EmployeeEntity) session.createCriteria(EmployeeEntity.class).list().get(number);
        session.getTransaction().commit();
        return employee;
    }
    public List<EmployeeEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<EmployeeEntity> employee = (List<EmployeeEntity>) session.createCriteria(EmployeeEntity.class).list();
        session.getTransaction().commit();
        return employee;
    }

}
