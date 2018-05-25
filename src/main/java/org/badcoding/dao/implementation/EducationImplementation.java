package org.badcoding.dao.implementation;


import org.badcoding.dao.interfaces.EducationInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.badcoding.utils.HibernateSessionFact;
import org.badcoding.dao.EducationEntity;
import java.util.List;

public class EducationImplementation implements EducationInterface{
    public void save(EducationEntity education) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(education);
            session.getTransaction().commit();
        }  finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(EducationEntity education) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(education);
            session.getTransaction().commit();
        }  finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(EducationEntity education) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(education);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public EducationEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct education "
                    + "from EducationEntity education "
                    + "where education.educationId = :id");
            query.setParameter("id", number);
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (EducationEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<EducationEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            List<EducationEntity> educations = (List<EducationEntity>) session.createCriteria(EducationEntity.class).list();
            session.getTransaction().commit();
            return educations;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
