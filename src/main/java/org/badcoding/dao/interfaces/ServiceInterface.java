package org.badcoding.dao.interfaces;

import org.badcoding.dao.ServiceEntity;

import java.util.List;

public interface ServiceInterface {
    void save(ServiceEntity service);
    void update(ServiceEntity service);
    void delete(ServiceEntity service);
    ServiceEntity getById(int id);
    ServiceEntity getByName(String name);
    List<ServiceEntity> getList();
}
