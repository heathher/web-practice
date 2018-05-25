package org.badcoding.utils;

import org.badcoding.dao.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateSessionFact
{
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static
    {
        try
        {
//          Configuration configuration = new Configuration();
            Configuration cfg=new Configuration().configure("/hibernate.cfg.xml");
            cfg.addAnnotatedClass(CustomerEntity.class);
            cfg.addAnnotatedClass(EducationEntity.class);
            cfg.addAnnotatedClass(EmployeeEntity.class);
            cfg.addAnnotatedClass(JobEntity.class);
            cfg.addAnnotatedClass(SalesOrderEntity.class);
            cfg.addAnnotatedClass(ServiceEntity.class);
            StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(
                    cfg.getProperties());
            sessionFactory= cfg.buildSessionFactory(builder.build());
        }
        catch (HibernateException he)
        {
            System.err.println("Error creating Session: " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}