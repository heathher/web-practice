package dao.implementation;

import dao.interfaces.SalesOrderInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.SalesOrderEntity;
import java.util.List;

public class SalesOrderImplementation implements SalesOrderInterface {
    public void save(SalesOrderEntity salesorder) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(salesorder);
        session.getTransaction().commit();
    }

    public void update(SalesOrderEntity salesorder) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(salesorder);
        session.getTransaction().commit();
    }
    public void delete(SalesOrderEntity salesorder) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(salesorder);
        session.getTransaction().commit();
    }
    public SalesOrderEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        SalesOrderEntity salesorder = (SalesOrderEntity) session.createCriteria(SalesOrderEntity.class).list().get(number);
        session.getTransaction().commit();
        return salesorder;
    }
    public List<SalesOrderEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<SalesOrderEntity> salesorder = (List<SalesOrderEntity>) session.createCriteria(SalesOrderEntity.class).list();
        session.getTransaction().commit();
        return salesorder;
    }
}
