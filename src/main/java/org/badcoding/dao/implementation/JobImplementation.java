package org.badcoding.dao.implementation;

import org.badcoding.dao.interfaces.JobInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.badcoding.utils.HibernateSessionFact;
import org.badcoding.dao.JobEntity;
import java.util.List;

public class JobImplementation implements JobInterface{
    public void save(JobEntity job) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(job);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(JobEntity job) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(job);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(JobEntity job) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(job);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public JobEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct job "
                    + "from JobEntity job "
                    + "where job.jobId = :id");
            query.setParameter("id", number);
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (JobEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<JobEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            List<JobEntity> jobs = (List<JobEntity>) session.createCriteria(JobEntity.class).list();
            session.getTransaction().commit();
            return jobs;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
