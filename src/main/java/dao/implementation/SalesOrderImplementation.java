package dao.implementation;

import dao.SalesOrderEntity;
import dao.interfaces.SalesOrderInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFact;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
                                               Date order_date) {
        Session session = null;
        List<SalesOrderEntity> result = new ArrayList<SalesOrderEntity>();
        try {
            String hsql_query =
                    "select u " +
                            "from SalesOrderEntity u ";
            if (customer_id != -1 )
                hsql_query += ", CustomerEntity c ";
            if (employee_id != -1)
                hsql_query += ", EmployeeEntity e ";
            if (service_id != -1)
                hsql_query += ", ServiceEntity s ";

            if (order_date != null)
                hsql_query += "where u.odrer_date like '%' || :order_date || '%' ";
            else
                hsql_query += "where 1 = 1 ";
            if (customer_id != -1)
                hsql_query += "and u.customer_id = c.customer_id";
            if (employee_id != -1)
                hsql_query += "and u.employee_id = e.employee_id";
            if (service_id != -1)
                hsql_query += "and u.service_id = s.service_id";

            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(hsql_query);

            if (order_date != null)
                query.setDate("order_date", order_date);

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
