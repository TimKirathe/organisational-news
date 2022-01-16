package dao;

import models.Department;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.*;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {
    private static Sql2oDepartmentDao sql2oDepartmentDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oDepartmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oDepartmentDao.clearAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void departmentGetsSavedAndReturnedProperlyFromDB() {
        Department testDepartment = setUpDepartment();
        sql2oDepartmentDao.save(testDepartment);
        Department returnedDepartment = sql2oDepartmentDao.findById(testDepartment.getId());
        assertTrue(testDepartment.equals(returnedDepartment));
    }

    public Department setUpDepartment() {
        Department testDepartment = new Department("Human Resource", "akjsdjkfh", 15);
        return testDepartment;
    }
}