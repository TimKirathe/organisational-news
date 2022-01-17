package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyNewsTest {

    @Test
    void getsInstantiatedProperly() {
        CompanyNews testCompanyNews = setUpCompanyNews();
        assertTrue(testCompanyNews instanceof CompanyNews);
    }

    @Test
    void gettersWorkCorrectly() {
        CompanyNews testCompanyNews = setUpCompanyNews();
        assertEquals("Employee Fired", testCompanyNews.getName());
    }

    @Test
    void equalsWorksAccordingly() {
        CompanyNews testCompanyNews1 = setUpCompanyNews();
        CompanyNews testCompanyNews2 = setUpCompanyNews();
        assertTrue(testCompanyNews1.equals(testCompanyNews2));
    }

    public CompanyNews setUpCompanyNews() {
        CompanyNews testCompanyNews = new CompanyNews("Employee Fired", "Caught sleeping on the job");
        return testCompanyNews;
    }

}