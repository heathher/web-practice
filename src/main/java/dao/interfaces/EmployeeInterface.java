package dao.interfaces;

import dao.EmployeeEntity;
import java.util.List;

public interface EmployeeInterface {
    void save(EmployeeEntity employee);
    void update(EmployeeEntity employee);
    void delete(EmployeeEntity employee);
    EmployeeEntity getById(int id);
    List<EmployeeEntity> getList();
}
