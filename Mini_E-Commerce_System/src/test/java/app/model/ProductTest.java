package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testProductCreation() {
        Product product = new Product(1, 2, "Laptop Pro", "16-inch, 16GB RAM", 1499.50, 30);
        assertEquals(1, product.getId());
        assertEquals(2, product.getCategoryId());
        assertEquals("Laptop Pro", product.getName());
        assertEquals("16-inch, 16GB RAM", product.getDescription());
        assertEquals(1499.50, product.getPrice(), 0.001);
        assertEquals(30, product.getStock());
    }
}
