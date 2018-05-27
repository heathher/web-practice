package org.badcoding.dao.implementation;

import org.badcoding.dao.EmployeeEntity;
import org.badcoding.dao.interfaces.EmployeeInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import org.badcoding.utils.HibernateSessionFact;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImplementation implements EmployeeInterface{
    public void save(EmployeeEntity employee) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(EmployeeEntity employee) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(EmployeeEntity employee) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public EmployeeEntity getById(int number) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct employee "
                    + "from EmployeeEntity employee "
                    + "where employee.employeeId = :id");
            query.setParameter("id", number);
            session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (EmployeeEntity)query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public EmployeeEntity getByName(String name) {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            //session.beginTransaction();
            Query query = session.createQuery("select distinct employee "
                    + "from EmployeeEntity employee "
                    + "where employee.lastname = :lastname");
            query.setParameter("lastname", name);
            //session.getTransaction().commit();
            if (query.list().size() == 0){
                return null;
            }
            return (EmployeeEntity) query.list().get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
    public List<EmployeeEntity> getList() {
        Session session = null;
        try {
            session = HibernateSessionFact.getSessionFactory().openSession();
            session.beginTransaction();
            List<EmployeeEntity> employeees = (List<EmployeeEntity>) session.createCriteria(EmployeeEntity.class).list();
            session.getTransaction().commit();
            return employeees;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeEntity> getByFilters(String firstName, String lastName,
                                             String mail, String phone,
                                             String address, Integer job_id,
                                             Integer education_id) {
        Session session = null;
        List<EmployeeEntity> result = new ArrayList<EmployeeEntity>();
        try {
            String hsql_query =
                    "select u " +
                            "from CustomerEntity u ";
            if (job_id != -1 )
                hsql_query += ", JobEntity j ";
            if (education_id != -1)
                hsql_query += ", EducationEntity e ";

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
            if (job_id != -1)
                hsql_query += "and u.job_id = j.job_id";
            if (education_id != -1)
                hsql_query += "and u.education_id = e.education_id";

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

    public List<EmployeeEntity> getByParameters(Date time_start, Date time_end, Integer customerId,
                                                Integer serviceId) throws SQLException {
        Session session = HibernateSessionFact.getSessionFactory().openSession();
        session.beginTransaction();
        boolean first=true;
        String string="select distinct b from EmployeeEntity b, SalesOrderEntity order inner join b.salesOrdersByEmployeeId contract ";
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
        if (customerId!=null) {
            if (!first)
                string+="and ";
            string+="contract.customerByCustomerId.customerId = :customerId ";
            first=false;
        }
        if (serviceId!=null) {
            if (!first)
                string+="and ";
            string+="contract.serviceByServiceId.serviceId = :serviceId ";
            first=false;
        }
        if (first)
            string="FROM EmployeeEntity ";
        Query query = session.createQuery(string);
        if (time_start!=null)
            query.setParameter("time_start",time_start);
        if (time_end!=null)
            query.setParameter("time_end",time_end);
        if (customerId!=null)
            query.setParameter("customerId",customerId);
        if (serviceId!=null)
            query.setParameter("serviceId",serviceId);
        return query.list();
    }

}
