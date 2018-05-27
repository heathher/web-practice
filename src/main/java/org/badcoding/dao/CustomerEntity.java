package org.badcoding.dao;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customer", schema = "lawfirm")
public class CustomerEntity {
    @GeneratedValue
    private int customerId;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String address;
    private Collection<SalesOrderEntity> salesOrdersByCustomerId;

    public CustomerEntity (){}
    public CustomerEntity(String firstName, String lastName,
                          String mail, String phone, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
    }

    @Id
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    @Column(name = "address", nullable = true, length = 45)
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
//        CustomerEntity that = (CustomerEntity) o;
//
//        if (customerId != that.customerId) return false;
//        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
//        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
//        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
//        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
//        if (address != null ? !address.equals(that.address) : that.address != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = customerId;
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (mail != null ? mail.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        result = 31 * result + (address != null ? address.hashCode() : 0);
//        return result;
//    }

    @OneToMany(cascade = { CascadeType.REMOVE },mappedBy = "customerByCustomerId")
    public Collection<SalesOrderEntity> getSalesOrdersByCustomerId() {
        return salesOrdersByCustomerId;
    }

    public void setSalesOrdersByCustomerId(Collection<SalesOrderEntity> salesOrdersByCustomerId) {
        this.salesOrdersByCustomerId = salesOrdersByCustomerId;
    }
}
