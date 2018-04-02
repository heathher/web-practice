package dao.implementation;

import dao.interfaces.ServiceInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.ServiceEntity;
import java.util.List;

public class ServiceImplementation implements ServiceInterface{
    public void save(ServiceEntity service) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(ServiceEntity service) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(service);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(ServiceEntity service) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(service);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public ServiceEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct service "
                    + "from ServiceEntity service "
                    + "where service.serviceId = :id");
            query.setParameter("id", number);
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (ServiceEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<ServiceEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            List<ServiceEntity> services = (List<ServiceEntity>) session.createCriteria(ServiceEntity.class).list();
            session.getTransaction().commit();
            return services;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
