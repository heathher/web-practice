package org.badcoding.dao.implementation;

import org.badcoding.dao.CustomerEntity;
import org.badcoding.dao.EmployeeEntity;
import org.badcoding.dao.SalesOrderEntity;
import org.badcoding.dao.ServiceEntity;
import org.testng.annotations.Test;
import java.sql.Date;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class SalesOrderTest {
    SalesOrderEntity salesOrder = null;
    SalesOrderImplementation sal_i = new SalesOrderImplementation();
    CustomerEntity customer = new CustomerImplementation().getById(1);
    EmployeeEntity employee = new EmployeeImplementation().getById(1);
    ServiceEntity service = new ServiceImplementation().getById(1);

    @Test
    public void testSave() throws Exception {
        int size = sal_i.getList().size();
        Date date = new Date(2016, 3, 21);
        salesOrder = new SalesOrderEntity(customer, employee, service, date);
        sal_i.save(salesOrder);
        int inserted_size = sal_i.getList().size();
        assertEquals(size+1, inserted_size);
    }

    @Test(dependsOnMethods = "testSave")
    public void testUpdate() throws Exception {
        Date newDate = new Date(2017, 4, 9);
        salesOrder = sal_i.getById(sal_i.getList().size());
        salesOrder.setOrderDate(newDate);
        sal_i.update(salesOrder);
        SalesOrderEntity updated = sal_i.getById(salesOrder.getId());
        assertEquals(newDate, updated.getOrderDate());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        int index = sal_i.getList().size();
        sal_i.delete(salesOrder);
        SalesOrderEntity deleted = sal_i.getById(index);
        assertNull(deleted);
    }
}