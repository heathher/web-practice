package dao.implementation;

import dao.CustomerEntity;
import dao.interfaces.CustomerInterface;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFact;

import java.util.ArrayList;
import java.util.List;

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
};
