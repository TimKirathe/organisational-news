package dao;

import models.Department;

public interface DepartmentDao {

    void save(Department department);

    void clearAll();
}
