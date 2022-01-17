package dao;

import models.News;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oNewsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oNewsDao.clearAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void newsSavedAndReturnedInDb() {
        News testNews = setUpNews();
        sql2oNewsDao.save(testNews);
        News savedNews = sql2oNewsDao.findById(testNews.getId());
        assertTrue(testNews.equals(savedNews));
    }

    public News setUpNews() {
        News testNews = new News("Headline", "New storage room built", 1);
        return testNews;
    }
}