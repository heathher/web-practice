package org.badcoding.dao.implementation;

import org.badcoding.dao.EducationEntity;
import org.badcoding.dao.EmployeeEntity;
import org.badcoding.dao.JobEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class EmployeeTest {
    EmployeeEntity employee = new EmployeeEntity();
    EmployeeImplementation emp_i = new EmployeeImplementation();
    JobEntity job = new JobImplementation().getById(1);
    EducationEntity education = new EducationImplementation().getById(1);

    @Test
    public void testSave() throws Exception {
        int size = emp_i.getList().size();
        employee = new EmployeeEntity("Sam", "Smith",
                "smith@mail.com","6648473", "Karlov st 23",
                job, education );
        emp_i.save(employee);
        int inserted_size = emp_i.getList().size();
        assertEquals(size+1, inserted_size);
    }

    @Test(dependsOnMethods = "testSave")
    public void testUpdate() throws Exception {
        employee = emp_i.getById(emp_i.getList().size());
        employee.setLastname("Jackman");
        emp_i.update(employee);
        EmployeeEntity updated = emp_i.getById((employee.getEmployeeId()));
        assertEquals("Jackman", updated.getLastname());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        int index = 90;
        emp_i.delete(emp_i.getById(emp_i.getList().size()   ));
        EmployeeEntity deleted = emp_i.getById(index);
        assertNull(deleted);
    }
}