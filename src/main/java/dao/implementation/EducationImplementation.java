package dao.implementation;

import dao.interfaces.EducationInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.EducationEntity;
import java.util.List;

public class EducationImplementation implements EducationInterface{
    public void save(EducationEntity education) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(education);
        session.getTransaction().commit();
    }

    public void update(EducationEntity education) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(education);
        session.getTransaction().commit();
    }
    public void delete(EducationEntity education) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(education);
        session.getTransaction().commit();
    }
    public EducationEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        EducationEntity education = (EducationEntity) session.createCriteria(EducationEntity.class).list().get(number);
        session.getTransaction().commit();
        return education;
    }
    public List<EducationEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<EducationEntity> education = (List<EducationEntity>) session.createCriteria(EducationEntity.class).list();
        session.getTransaction().commit();
        return education;
    }
}
