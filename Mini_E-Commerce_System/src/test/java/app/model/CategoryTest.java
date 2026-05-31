package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void testCategoryCreation() {
        Category cat = new Category(1, "Electronics", "Gadgets and devices");
        assertEquals(1, cat.getId());
        assertEquals("Electronics", cat.getName());
        assertEquals("Gadgets and devices", cat.getDescription());
    }
}
