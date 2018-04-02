package dao.implementation;

import dao.JobEntity;
import dao.EmployeeEntity;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class JobTest {
    JobImplementation job_i = new JobImplementation();
    JobEntity job = null;
    EmployeeEntity employee = new EmployeeImplementation().getById(1);

    @Test
    public void testSave() throws Exception {
        int size = job_i.getList().size();
        job = new JobEntity(10, "lawyer");
        job_i.save(job);
        int inserted_size = job_i.getList().size();
        assertEquals(size+1, inserted_size);
    }

    @Test(dependsOnMethods = "testSave")
    public void testUpdate() throws Exception {
        job = job_i.getById(10);
        job.setFunction("lawyer on phone");
        job_i.update(job);
        JobEntity updated = job_i.getById(job.getJobId());
        assertEquals("lawyer on phone", job.getFunction());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        int index = 10;
        job_i.delete(job_i.getById(10));
        JobEntity deleted = job_i.getById(index);
        assertNull(deleted);
    }
}