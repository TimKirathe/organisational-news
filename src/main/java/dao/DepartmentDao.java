package dao;

import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {

    //CREATE
    void save(Department department);
    void saveNewsAndDepartment(Department department, News news);

    //READ
    Department findById(int id);
    List<Department> getAll();
    List<News> getAllDepartmentNews(int departmentId);

    //DESTROY
    void clearAll();
}
