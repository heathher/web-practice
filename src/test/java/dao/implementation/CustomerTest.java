package dao.implementation;

import dao.CustomerEntity;
import dao.interfaces.CustomerInterface;
import org.testng.annotations.Test;
import java.lang.String;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CustomerTest {
    CustomerInterface cust_i = new CustomerImplementation();

    @Test
    public void testSave(){
        int size = cust_i.getList().size();
        CustomerEntity customer = new CustomerEntity(90, "Sam", "Smith",
                "smith@mail.com", "6648473", "Karlov st 23");
        cust_i.save(customer);
        int size_insert = cust_i.getList().size();
        assertEquals(size+1, size_insert);
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testUpdate() throws Exception {
        CustomerEntity customer = cust_i.getById(90);
        String newMail = "newmail@mail.com";
        customer.setMail(newMail);
        cust_i.update(customer);
        CustomerEntity updated = cust_i.getById(customer.getCustomerId());
        assertEquals(newMail, updated.getMail());
    }

    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() throws Exception {
        int index = 90;
        cust_i.delete(cust_i.getById(index));
        CustomerEntity deleted = cust_i.getById(index);
        assertNull(deleted);
    }

    @Test(dependsOnMethods = {"testDelete"})
    public void testGetList() throws Exception {
        int size1 = cust_i.getList().size();
        int size2 = cust_i.getList().size();
        assertEquals(size1, size2);
    }
}