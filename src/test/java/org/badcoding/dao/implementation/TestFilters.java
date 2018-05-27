package org.badcoding.dao.implementation;

import org.badcoding.dao.CustomerEntity;
import org.badcoding.dao.EmployeeEntity;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestFilters {
    CustomerImplementation cust_i = new CustomerImplementation();
    EmployeeImplementation emp_i = new EmployeeImplementation();

    @Test
    public void testCustomerFilters() throws Exception{
        List<CustomerEntity> customers1 = cust_i.getByParameters(new java.sql.Date(2017-1-1),new java.sql.Date(2018-4-1), null, null);
        assertEquals(1, customers1.size());
        List<EmployeeEntity> employee = emp_i.getByParameters(null, null, null, null);
        assertEquals(5, employee.size());
    }
}
