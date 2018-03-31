package dao.interfaces;

import dao.ServiceEntity;

import java.util.List;

public interface ServiceInterface {
    void save(ServiceEntity service);
    void update(ServiceEntity service);
    void delete(ServiceEntity service);
    ServiceEntity getById(int id);
    List<ServiceEntity> getList();
}
