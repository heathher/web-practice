package dao.interfaces;

import dao.CustomerEntity;

import java.util.List;

public interface CustomerInterface {
    void save(CustomerEntity customer);
    void update(CustomerEntity customer);
    void delete(CustomerEntity customer);
    CustomerEntity getById(int id);
    List<CustomerEntity> getList();
}
