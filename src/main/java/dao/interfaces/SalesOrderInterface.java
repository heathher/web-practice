package dao.interfaces;

import dao.SalesOrderEntity;

import java.util.List;

public interface SalesOrderInterface {
    void save(SalesOrderEntity salesOrder);
    void update(SalesOrderEntity salesOrder);
    void delete(SalesOrderEntity salesOrder);
    SalesOrderEntity getById(int id);
    List<SalesOrderEntity> getList();
}
