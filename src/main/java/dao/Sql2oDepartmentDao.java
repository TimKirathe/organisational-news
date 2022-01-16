package dao;

import models.Department;
import org.sql2o.*;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Department department) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO departments (name, description, noOfEmployees) VALUES (:name, :description, :noOfEmployees)";
            int id = (int) con.createQuery(sql)
                    .bind(department).executeUpdate().getKey();
            department.setId(id);
        }
    }

    @Override
    public Department findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM departments WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id).executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM departments";
            return con.createQuery(sql).executeAndFetch(Department.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "TRUNCATE TABLE departments";
        String sql2 = "ALTER SEQUENCE departments_id_seq RESTART";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
}
