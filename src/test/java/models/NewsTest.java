package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {

    @Test
    void newsInstantiatedCorrectly() {
        News testNews = setUpNews();
        assertTrue(testNews instanceof News);
    }

    @Test
    void gettersWorkCorrectly() {
        News testNews = setUpNews();
        assertEquals("Headline", testNews.getName());
    }

    @Test
    void equalsWorksCorrectly() {
        News testNews1 = setUpNews();
        News testNews2 = setUpNews();
        assertTrue(testNews1.equals(testNews2));
    }

    public News setUpNews() {
        News testNews = new News("Headline", "New storage room built", 1);
        return testNews;
    }

}