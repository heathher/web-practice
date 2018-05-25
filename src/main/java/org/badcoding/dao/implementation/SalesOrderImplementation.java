package org.badcoding.dao.implementation;

import org.badcoding.dao.SalesOrderEntity;
import org.badcoding.dao.interfaces.SalesOrderInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.badcoding.utils.HibernateSessionFact;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderImplementation implements SalesOrderInterface {
    public void save(SalesOrderEntity salesorder) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(salesorder);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(SalesOrderEntity salesorder) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(salesorder);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(SalesOrderEntity salesorder) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(salesorder);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public SalesOrderEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct salesOrder "
                    + "from SalesOrderEntity salesOrder "
                    + "where salesOrder.id = :id");
            query.setParameter("id", number);
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (SalesOrderEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<SalesOrderEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            List<SalesOrderEntity> sales = (List<SalesOrderEntity>) session.createCriteria(SalesOrderEntity.class).list();
            session.getTransaction().commit();
            return sales;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<SalesOrderEntity> getByFilters(Integer customer_id,
                                               Integer employee_id,
                                               Integer service_id,
                                               Date beginOrderDate,
                                               Date endOrderDate) {
        Session session = null;
        List<SalesOrderEntity> result = new ArrayList<SalesOrderEntity>();
        try {
            String hsql_query =
                    "select u " +
                            "from SalesOrderEntity u ";
            if (customer_id != -1)
                hsql_query += " where u.customerByCustomerId.customerId = " +  customer_id ;
            else
                hsql_query += " where 1 = 1 ";
            if (employee_id != -1)
                hsql_query += " and u.employeeByEmployeeId.employeeId = " + employee_id ;
            if (service_id != -1)
                hsql_query += " and u.serviceByServiceId.serviceId = " + service_id ;
            if (beginOrderDate != null && endOrderDate != null) {
                hsql_query += " and u.orderDate > '' || :beginOrderDate || '' ";
                hsql_query += " and u.orderDate < '' || :endOrderDate || '' ";
            }

            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(hsql_query);


            System.out.println("query: " + hsql_query);
            result = query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }
}
