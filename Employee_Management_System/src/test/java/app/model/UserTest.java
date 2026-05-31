package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testUserCreation() {
        User user = new User(1, "admin", "ADMIN");
        assertEquals(1, user.getId());
        assertEquals("admin", user.getUsername());
        assertEquals("ADMIN", user.getRole());
    }
}
