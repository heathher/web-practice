package dao;

import dao.implementation.EmployeeImplementation;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "lawfirm", catalog = "")
public class EmployeeEntity {
    private int employeeId;
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;
    private String address;
    private JobEntity jobByJobId;
    private EducationEntity educationByEducationId;
    private Collection<SalesOrderEntity> salesOrdersByEmployeeId;

    public EmployeeEntity(){}

    public EmployeeEntity(int employeeId, String firstname, String lastname,
                          String mail, String phone, String address,
                          JobEntity job, EducationEntity education){
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.jobByJobId = job;
        this.educationByEducationId = education;
    }


    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 45)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 45)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        EmployeeEntity that = (EmployeeEntity) o;
//
//        if (employeeId != that.employeeId) return false;
//        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
//        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
//        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
//        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
//        if (address != null ? !address.equals(that.address) : that.address != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = employeeId;
//        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
//        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
//        result = 31 * result + (mail != null ? mail.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        result = 31 * result + (address != null ? address.hashCode() : 0);
//        return result;
//    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id", nullable = false)
    public JobEntity getJobByJobId() {
        return jobByJobId;
    }

    public void setJobByJobId(JobEntity jobByJobId) {
        this.jobByJobId = jobByJobId;
    }

    @ManyToOne
    @JoinColumn(name = "education_id", referencedColumnName = "education_id", nullable = false)
    public EducationEntity getEducationByEducationId() {
        return educationByEducationId;
    }

    public void setEducationByEducationId(EducationEntity educationByEducationId) {
        this.educationByEducationId = educationByEducationId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<SalesOrderEntity> getSalesOrdersByEmployeeId() {
        return salesOrdersByEmployeeId;
    }

    public void setSalesOrdersByEmployeeId(Collection<SalesOrderEntity> salesOrdersByEmployeeId) {
        this.salesOrdersByEmployeeId = salesOrdersByEmployeeId;
    }
}
