package org.badcoding.dao.implementation;

import org.badcoding.dao.CustomerEntity;
import org.badcoding.dao.EmployeeEntity;
import org.badcoding.dao.ServiceEntity;
import org.badcoding.dao.interfaces.CustomerInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.badcoding.utils.HibernateSessionFact;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Service
public class CustomerImplementation implements CustomerInterface{

    public void save(CustomerEntity customer) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void update(CustomerEntity customer) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
    public void delete(CustomerEntity customer) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
    public CustomerEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            //session.beginTransaction();
            Query query = session.createQuery("select distinct customer "
            + "from CustomerEntity customer "
            + "where customer.customerId = :id");
            query.setParameter("id", number);
            //session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (CustomerEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public CustomerEntity getByName(String name) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            //session.beginTransaction();
            Query query = session.createQuery("select distinct customer "
                    + "from CustomerEntity customer "
                    + "where customer.lastName = :last_name");
            query.setParameter("last_name", name);
            //session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (CustomerEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
    public List<CustomerEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            //List<CustomerEntity> customers = (List<CustomerEntity>) session.createCriteria(CustomerEntity.class).list();
            Query query = session.createQuery("select distinct customer "
                    + "from CustomerEntity customer ");
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (List<CustomerEntity>)query.list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<CustomerEntity> getByFilters(String firstName, String lastName,
                                             String mail, String phone,
                                             String address) {
        Session session = null;
        List<CustomerEntity> result = new ArrayList<CustomerEntity>();
        try {
            String hsql_query =
                    "select u " +
                            "from CustomerEntity u ";

            if (firstName != null)
                hsql_query += "where u.firstName like '%' || :firstName || '%' ";
            else
                hsql_query += "where 1 = 1 ";
            if (lastName != null)
                hsql_query += "and u.lastName like '%' || :lastName || '%' ";
            if (mail != null)
                hsql_query += "and u.mail like '%' || :mail || '%' ";
            if (phone != null)
                hsql_query += "and u.phone like '%' || :phone || '%' ";


            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(hsql_query);

            if (firstName != null)
                query.setString("firstName", firstName);
            if (lastName != null)
                query.setString("lastName", lastName);
            if (mail != null)
                query.setString("mail", mail);
            if (phone != null)
                query.setString("phone", phone);
            if (address != null)
                query.setString("address", address);

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

    public List<CustomerEntity> getByParameters(Date time_start, Date time_end, Integer employeeId,
                                                Integer serviceId) throws SQLException {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        boolean first=true;
        String string="select distinct b from CustomerEntity b, SalesOrderEntity order inner join b.salesOrdersByCustomerId contract ";
        string+="where ";
        if (time_start!=null) {
            string+="contract.orderDate >= :time_start ";
            first=false;
        }
        if (time_end!=null) {
            if (!first)
                string+="and ";
            string+="(contract.orderDate=null or contract.orderDate <= :time_end) ";
            first=false;
        }
        if (employeeId!=null) {
            if (!first)
                string+="and ";
            string+="contract.employeeByEmployeeId.employeeId = :employeeId ";
            first=false;
        }
        if (serviceId!=null) {
            if (!first)
                string+="and ";
            string+="contract.serviceByServiceId.serviceId = :serviceId ";
            first=false;
        }
        if (first)
            string="FROM CustomerEntity ";
        Query query = session.createQuery(string);
        if (time_start!=null)
            query.setParameter("time_start",time_start);
        if (time_end!=null)
            query.setParameter("time_end",time_end);
        if (employeeId!=null)
            query.setParameter("employeeId",employeeId);
        if (serviceId!=null)
            query.setParameter("serviceId",serviceId);
        return query.list();
    }
};
