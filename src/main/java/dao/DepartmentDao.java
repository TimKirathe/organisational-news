package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {

    //CREATE
    void save(Department department);

    //READ
    Department findById(int id);
    List<Department> getAll();

    //DESTROY
    void clearAll();
}
