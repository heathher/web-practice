package org.badcoding.dao.implementation;

import org.badcoding.dao.EducationEntity;
import org.badcoding.dao.EmployeeEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class EducationTest {
    EducationImplementation edu_i = new EducationImplementation();
    EducationEntity education = null;
    EmployeeEntity employee = new EmployeeImplementation().getById(1);

    @Test
    public void testSave() throws Exception {
        int size = edu_i.getList().size();
        education = new EducationEntity("Doctor");
        edu_i.save(education);
        int inserted_size = edu_i.getList().size();
        assertEquals(size+1, inserted_size);
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testUpdate() throws Exception {
        education = edu_i.getById(edu_i.getList().size());
        education.setGrade("Lawyer");
        edu_i.update(education);
        EducationEntity updated = edu_i.getById(education.getEducationId());
        assertEquals("Lawyer", updated.getGrade());
    }

    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() throws Exception {
        int index = 10;
        edu_i.delete(edu_i.getById(edu_i.getList().size()));
        EducationEntity deleted = edu_i.getById(index);
        assertNull(deleted);
    }
}