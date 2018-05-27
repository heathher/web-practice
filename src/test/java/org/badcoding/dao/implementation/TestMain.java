package org.badcoding.dao.implementation;

import org.badcoding.dao.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestMain extends Assert{
    @DataProvider
    public Object[][] testDataSalesOrder(){
        return new Object[][]{
                {1, -1, -1, null, null, 0},
                {-1, 3, -1, null, null, 1},
                {2, -1, 3, null, null, 2},
                {2, -1, -1, null, null,  3},
                {-1, 4, 4, null, null, 4},
        };
    }
    private static List<Integer> init_stats = new ArrayList<Integer>();

    @Test
    public void addData(){
        addCustomer("Daniel", "Peters",
                "dhs@mail.com", "5393323", "jdf" );
        addCustomer("Dan", "Stone",
                "lkkj@mail.com", "74365", "sdgfg" );
        addEmployee("Michael", "Jackson",
                "sdjfhs@mail.com", "483504", "jdf" , 2, 3);
        addEmployee("Mickey", "Mouse",
                "lsjflhs@mail.com", "3454504", "jdf" , 2, 2);
        addSalesOrder(1, 1, 2, Date.valueOf("2016-04-04"));
    }

    @AfterClass
    public void removeData(){
        CustomerImplementation cust_i = new CustomerImplementation();
        EmployeeImplementation emp_i = new EmployeeImplementation();
        SalesOrderImplementation sal_i = new SalesOrderImplementation();
        removeCustomer(cust_i.getList().size());
        removeCustomer(cust_i.getList().size()-1);
        removeEmployee(emp_i.getList().size());
        removeEmployee(emp_i.getList().size()-1);
        removeSalesOrder(sal_i.getList().size());
    }


    @Test(dataProvider = "testDataSalesOrder")
    public void getInitSalesOrders(Integer customer_id,
                                   Integer employee_id, Integer service_id,
                                   Date beginOrderDate, Date endOrderDate,
                                   int i){
        List<SalesOrderEntity> result = new SalesOrderImplementation().getByFilters(customer_id,
                employee_id, service_id, beginOrderDate, endOrderDate);
        if (result == null)
            init_stats.add(0);
        else
            init_stats.add(result.size());
    }

    @Test(dataProvider = "testDataSalesOrder")
    public void review_changes(Integer customer_id,
                               Integer employee_id, Integer service_id,
                               Date beginOrderDate, Date endOrderDate, int i) {
        List<SalesOrderEntity> result = new SalesOrderImplementation().getByFilters(customer_id,
                employee_id, service_id, beginOrderDate, endOrderDate);
        System.out.println(i + ": " + result.size());
        assertEquals(result.size(), (int)init_stats.get(i) );
    }

    // add customer or employee
    public void addCustomer(String firstName, String lastName,
                            String mail, String phone,
                            String address){
        CustomerEntity customer = new CustomerEntity(firstName, lastName, mail, phone, address);
        CustomerImplementation cust_i = new CustomerImplementation();
        cust_i.save(customer);
    }
    public void addEmployee(String firstname, String lastname,
                            String mail, String phone, String address,
                            Integer job_id, Integer education_id){
        JobEntity job = new JobImplementation().getById(job_id);
        EducationEntity education = new EducationImplementation().getById(education_id);
        EmployeeEntity employee = new EmployeeEntity(firstname, lastname,
                mail, phone, address, job, education);
        EmployeeImplementation emp_i = new EmployeeImplementation();
        emp_i.save(employee);
    }

    // remove customer or employee
    public void removeCustomer(Integer customer_id){
        CustomerImplementation cust_i = new CustomerImplementation();
        cust_i.delete(cust_i.getById(customer_id));
    }
    public void removeEmployee(Integer employee_id){
        EmployeeImplementation emp_i = new EmployeeImplementation();
        emp_i.delete(emp_i.getById(employee_id));
    }

    // update customer or employee
    public void updateCustomer(int customer_id, String firstName, String lastName,
                               String mail, String phone,
                               String address){
        CustomerEntity customer = new CustomerEntity(firstName, lastName, mail, phone, address);
        CustomerImplementation cust_i = new CustomerImplementation();
        cust_i.update(customer);
    }
    public void updateEmployee(int employee_id, String firstname, String lastname,
                               String mail, String phone, String address,
                               JobEntity job, EducationEntity education){
        EmployeeEntity employee = new EmployeeEntity(firstname, lastname,
                mail, phone, address, job, education);
        EmployeeImplementation emp_i = new EmployeeImplementation();
        emp_i.update(employee);
    }

    // read customer or employee by id
    public CustomerEntity getByIdCustomer(int id){
        CustomerImplementation cust_i = new CustomerImplementation();
        CustomerEntity customer = cust_i.getById(id);
        return customer;
    }
    public EmployeeEntity getByIdEmployee(int id){
        EmployeeImplementation emp_i = new EmployeeImplementation();
        EmployeeEntity employee = emp_i.getById(id);
        return employee;
    }

    // add sales order
    public void addSalesOrder(Integer customer_id,
                              Integer employee_id, Integer service_id,
                              Date orderDate){
        CustomerEntity customer = new CustomerImplementation().getById(customer_id);
        EmployeeEntity employee = new EmployeeImplementation().getById(employee_id);
        ServiceEntity service = new ServiceImplementation().getById(service_id);
        SalesOrderEntity salesOrder = new SalesOrderEntity(customer, employee,
                service, orderDate);
        SalesOrderImplementation sal_i = new SalesOrderImplementation();
        sal_i.save(salesOrder);
    }

    //remove sales order
    public void removeSalesOrder(int salesOrder_id){
        SalesOrderImplementation sal_i = new SalesOrderImplementation();
        sal_i.delete(sal_i.getById(salesOrder_id));
    }

    // get list of sales orders with filters
    List<SalesOrderEntity> getSalesOrdersByFilters(CustomerEntity customer,
                                               EmployeeEntity employee, ServiceEntity service,
                                               Date beginOrderDate, Date endOrderDate){
        List<SalesOrderEntity> result = new SalesOrderImplementation().getByFilters(customer.getCustomerId(),
                employee.getEmployeeId(), service.getServiceId(), beginOrderDate, endOrderDate);
        return result;
    }

    /*
    //get list of employees with filters
    List<EmployeeEntity> getEmployeesByFilters(String firstname, String lastname,
                                               String mail, String phone, String address,
                                               JobEntity job, EducationEntity education){
        List<EmployeeEntity> result = new EmployeeImplementation().getByFilters(firstname,
                lastname, mail, phone, address, job.getJobId(), education.getEducationId());
        return result;
    }

    //get list of customers with filters
    List<CustomerEntity> getCustomersByFilters(String firstname, String lastname,
                                               String mail, String phone, String address){
        List<CustomerEntity> result = new CustomerImplementation().getByFilters(firstname,
                lastname, mail, phone, address);
        return result;
    }
    */
}
