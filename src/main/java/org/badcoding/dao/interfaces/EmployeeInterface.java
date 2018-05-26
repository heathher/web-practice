package org.badcoding.dao.interfaces;

import org.badcoding.dao.EmployeeEntity;
import java.util.List;

public interface EmployeeInterface {
    void save(EmployeeEntity employee);
    void update(EmployeeEntity employee);
    void delete(EmployeeEntity employee);
    EmployeeEntity getById(int id);
    EmployeeEntity getByName(String name);
    List<EmployeeEntity> getList();
}
