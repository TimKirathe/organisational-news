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

    @Test
    void equalsWorksCorrectly() {
        User testUser1 = setUpUser();
        User testUser2 = setUpUser();
        assertTrue(testUser1.equals(testUser2));
    }

    public User setUpUser() {
        User testUser = new User("Timothy", "Tech", "Employee", 1);
        return testUser;
    }

}