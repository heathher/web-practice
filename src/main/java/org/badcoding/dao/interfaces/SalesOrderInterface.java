package org.badcoding.dao.interfaces;

import org.badcoding.dao.SalesOrderEntity;

import java.util.List;

public interface SalesOrderInterface {
    void save(SalesOrderEntity salesOrder);
    void update(SalesOrderEntity salesOrder);
    void delete(SalesOrderEntity salesOrder);
    SalesOrderEntity getById(int id);
    List<SalesOrderEntity> getList();
}
