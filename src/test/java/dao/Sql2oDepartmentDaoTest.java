package dao;

import models.Department;
import models.News;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {
    private static Sql2oDepartmentDao sql2oDepartmentDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oDepartmentDao = new Sql2oDepartmentDao(sql2o);
        sql2oNewsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oDepartmentDao.clearAll();
        sql2oNewsDao.clearAll();
        sql2oDepartmentDao.clearAllNewsAndDepartments();
        sql2oDepartmentDao.clearAllUsersAndDepartments();
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

    @Test
    void allNewsInDepartmentGetsSavedInDbAndReturned() {
        List<News> savedNews = new ArrayList<>();
        Department testDepartment = setUpDepartment();
        sql2oDepartmentDao.save(testDepartment);
        News testNews = new News("Employee fIRED!", "Caught stealing funds", testDepartment.getId());
        News testNews2 = new News("Breakthrough", "Heroku deployment solved", testDepartment.getId());
        News testNews3 = new News("Fallout", "CEO and Human Resource Manager altercation", testDepartment.getId());
        sql2oNewsDao.save(testNews);
        sql2oNewsDao.save(testNews2);
        sql2oNewsDao.save(testNews3);
        sql2oDepartmentDao.saveNewsAndDepartment(testDepartment, testNews);
        sql2oDepartmentDao.saveNewsAndDepartment(testDepartment, testNews2);
        sql2oDepartmentDao.saveNewsAndDepartment(testDepartment, testNews3);
        List<News> returnedNews = sql2oDepartmentDao.getAllDepartmentNews(testDepartment.getId());
        savedNews.add(testNews);
        savedNews.add(testNews2);
        savedNews.add(testNews3);
        assertEquals(savedNews, returnedNews);
    }

    public Department setUpDepartment() {
        Department testDepartment = new Department("Human Resource", "akjsdjkfh", 15);
        return testDepartment;
    }
}