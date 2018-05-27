package org.badcoding.dao;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "service", schema = "lawfirm", catalog = "")
public class ServiceEntity {
    @GeneratedValue
    private int serviceId;
    private String serviceName;
    private int servicePrice;
    private Collection<SalesOrderEntity> salesOrdersByServiceId;

    public ServiceEntity(){}
    public ServiceEntity(String serviceName, int servicePrice){
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }
    @Id
    @Column(name = "service_id", nullable = false)
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "service_name", nullable = true, length = 45)
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Basic
    @Column(name = "service_price", nullable = false)
    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ServiceEntity that = (ServiceEntity) o;
//
//        if (serviceId != that.serviceId) return false;
//        if (servicePrice != that.servicePrice) return false;
//        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = serviceId;
//        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
//        result = 31 * result + servicePrice;
//        return result;
//    }

    @OneToMany(mappedBy = "serviceByServiceId")
    public Collection<SalesOrderEntity> getSalesOrdersByServiceId() {
        return salesOrdersByServiceId;
    }

    public void setSalesOrdersByServiceId(Collection<SalesOrderEntity> salesOrdersByServiceId) {
        this.salesOrdersByServiceId = salesOrdersByServiceId;
    }
}
