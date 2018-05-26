package org.badcoding.dao;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "education", schema = "lawfirm", catalog = "")
public class EducationEntity {
    private int educationId;
    private String grade;
    private Collection<EmployeeEntity> employeesByEducationId;

    public EducationEntity(){}

    public EducationEntity(int educationId, String grade){
        this.educationId = educationId;
        this.grade = grade;
    }

    @Id
    @Column(name = "education_id", nullable = false)
    @GeneratedValue
    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 45)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        EducationEntity that = (EducationEntity) o;
//
//        if (educationId != that.educationId) return false;
//        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = educationId;
//        result = 31 * result + (grade != null ? grade.hashCode() : 0);
//        return result;
//    }

    @OneToMany(mappedBy = "educationByEducationId")
    public Collection<EmployeeEntity> getEmployeesByEducationId() {
        return employeesByEducationId;
    }

    public void setEmployeesByEducationId(Collection<EmployeeEntity> employeesByEducationId) {
        this.employeesByEducationId = employeesByEducationId;
    }
}
