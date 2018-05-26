package org.badcoding.dao.implementation;

import org.badcoding.dao.SalesOrderEntity;
import org.badcoding.dao.ServiceEntity;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class ServiceTest {
    ServiceEntity service = null;
    ServiceImplementation serv_i = new ServiceImplementation();
    SalesOrderEntity salesOrder = new SalesOrderImplementation().getById(1);

    @Test
    public void testSave() throws Exception {
        int size = serv_i.getList().size();
        service = new ServiceEntity(30, "Calling", 50);
        serv_i.save(service);
        int inserted_size = serv_i.getList().size();
        assertEquals(size+1, inserted_size);
    }

    @Test(dependsOnMethods = "testSave")
    public void testUpdate() throws Exception {
        service = serv_i.getById(30);
        service.setServicePrice(100);
        serv_i.update(service);
        ServiceEntity updated = serv_i.getById(service.getServiceId());
        assertEquals(100, updated.getServicePrice());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        int index = 30;
        serv_i.delete(service);
        ServiceEntity deleted = serv_i.getById(index);
        assertNull(deleted);
    }
    @Test(dependsOnMethods = "testDelete")
    public void testGetByName() throws Exception {
        String name = "Consultation";
        ServiceEntity service = serv_i.getByName(name);
        assertEquals(service.getServiceName(), name);
    }

}