package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {
    @Test
    void testCartItemCreation() {
        CartItem item = new CartItem(1, 10, 5, "Laptop Pro", 2, 1499.50);
        assertEquals(1, item.getId());
        assertEquals(10, item.getCartId());
        assertEquals(5, item.getProductId());
        assertEquals("Laptop Pro", item.getProductName());
        assertEquals(2, item.getQuantity());
        assertEquals(1499.50, item.getUnitPrice(), 0.001);
    }
}
