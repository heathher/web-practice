package main;

import dao.*;
import dao.implementation.*;
import dao.interfaces.*;
import org.hibernate.Session;
import utils.HibernateSessionFact;

public class Test{
    CustomerEntity customer;
    EducationEntity education;
    JobInterface job;
    EmployeeEntity employee;
    SalesOrderEntity salesOrder;
    ServiceEntity service;

    CustomerInterface cust_i = new CustomerImplementation();
    EducationInterface educ_i = new EducationImplementation();
    EmployeeInterface emp_i = new EmployeeImplementation();
    JobInterface job_i = new JobImplementation();
    SalesOrderInterface sal_i = new SalesOrderImplementation();
    ServiceInterface ser_i = new ServiceImplementation();

    

}

//public class TestMain {
//    public static void main(String[] args) {
//        System.out.println("Main");
//
//        Session session = HibernateSessionFact.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        ServiceEntity serviceEntity = new ServiceEntity();
//
//        serviceEntity.setServiceName("Consulting with lawyer");
//        serviceEntity.setServicePrice(150);
//
//        session.save(serviceEntity);
//        session.getTransaction().commit();
//        session.close();
//
//
//    }
//}