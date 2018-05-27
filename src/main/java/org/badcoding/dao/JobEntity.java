package org.badcoding.dao;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "job", schema = "lawfirm", catalog = "")
public class JobEntity {
    @GeneratedValue
    private int jobId;
    private String function;
    private Collection<EmployeeEntity> employeesByJobId;

    public JobEntity(){}

    public JobEntity(String function){
        this.function = function;
    }

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "function", nullable = true, length = 30)
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        JobEntity jobEntity = (JobEntity) o;
//
//        if (jobId != jobEntity.jobId) return false;
//        if (function != null ? !function.equals(jobEntity.function) : jobEntity.function != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = jobId;
//        result = 31 * result + (function != null ? function.hashCode() : 0);
//        return result;
//    }

    @OneToMany(mappedBy = "jobByJobId")
    public Collection<EmployeeEntity> getEmployeesByJobId() {
        return employeesByJobId;
    }

    public void setEmployeesByJobId(Collection<EmployeeEntity> employeesByJobId) {
        this.employeesByJobId = employeesByJobId;
    }
}
