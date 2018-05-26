package org.badcoding.dao.interfaces;

import org.badcoding.dao.CustomerEntity;

import java.util.List;

public interface CustomerInterface {
    void save(CustomerEntity customer);
    void update(CustomerEntity customer);
    void delete(CustomerEntity customer);
    CustomerEntity getById(int id);
    CustomerEntity getByName(String name);
    List<CustomerEntity> getList();
}
