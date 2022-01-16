package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void departmentInstantiatedCorrectly() {
        Department testDepartment = setUpDepartment();
        assertTrue(testDepartment instanceof Department);
    }

    @Test
    void gettersWorkAccordingly() {
        Department testDepartment = setUpDepartment();
        assertEquals(15, testDepartment.getNoOfEmployees());
    }

    @Test
    void equalsWorksCorrectly() {
        Department testDepartment1 = setUpDepartment();
        Department testDepartment2 = new Department("Human Resource", "jkashdkjf", 15);
        assertFalse(testDepartment1.equals(testDepartment2));
    }

    public Department setUpDepartment() {
        Department testDpeartment = new Department("Human Resource", "akjsdjkfh", 15);
        return testDpeartment;
    }
}