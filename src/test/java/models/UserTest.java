package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userInstantiatedCorrectly() {
        User testUser = setUpUser();
        assertTrue(testUser instanceof User);
    }

    @Test
    void gettersWorkCorrectly() {
        User testUser = setUpUser();
        assertEquals("Timothy", testUser.getName());
    }

    public User setUpUser() {
        User testUser = new User("Timothy", "Tech", "Employee", 1);
        return testUser;
    }

}