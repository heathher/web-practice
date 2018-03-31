package dao.implementation;

import dao.interfaces.ServiceInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.ServiceEntity;
import java.util.List;

public class ServiceImplementation implements ServiceInterface{
    public void save(ServiceEntity service) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
    }

    public void update(ServiceEntity service) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(service);
        session.getTransaction().commit();
    }
    public void delete(ServiceEntity service) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(service);
        session.getTransaction().commit();
    }
    public ServiceEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        ServiceEntity service = (ServiceEntity) session.createCriteria(ServiceEntity.class).list().get(number);
        session.getTransaction().commit();
        return service;
    }
    public List<ServiceEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<ServiceEntity> service = (List<ServiceEntity>) session.createCriteria(ServiceEntity.class).list();
        session.getTransaction().commit();
        return service;
    }
}
