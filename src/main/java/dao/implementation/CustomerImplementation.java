package dao.implementation;

import dao.CustomerEntity;
import dao.interfaces.CustomerInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import java.util.List;

public class CustomerImplementation implements CustomerInterface{

    public void save(CustomerEntity customer) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
    }

    public void update(CustomerEntity customer) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
    }
    public void delete(CustomerEntity customer) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
    }
    public CustomerEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        CustomerEntity customer = (CustomerEntity) session.createCriteria(CustomerEntity.class).list().get(number);
        session.getTransaction().commit();
        return customer;
    }
    public List<CustomerEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<CustomerEntity> customer = (List<CustomerEntity>) session.createCriteria(CustomerEntity.class).list();
        session.getTransaction().commit();
        return customer;
    }
};
