package dao.implementation;

import dao.interfaces.JobInterface;
import org.hibernate.Session;
import utils.HibernateSessionFact;
import dao.JobEntity;
import java.util.List;

public class JobImplementation implements JobInterface{
    public void save(JobEntity job) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(job);
        session.getTransaction().commit();
    }

    public void update(JobEntity job) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(job);
        session.getTransaction().commit();
    }
    public void delete(JobEntity job) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(job);
        session.getTransaction().commit();
    }
    public JobEntity getById(int number) {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        JobEntity job = (JobEntity) session.createCriteria(JobEntity.class).list().get(number);
        session.getTransaction().commit();
        return job;
    }
    public List<JobEntity> getList() {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        List<JobEntity> job = (List<JobEntity>) session.createCriteria(JobEntity.class).list();
        session.getTransaction().commit();
        return job;
    }
}
