package app.model;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void testBookCreation() {
        Book book = new Book(1, "Effective Java", "Joshua Bloch", "978-0134685991", 2017, 5, 3);
        assertEquals(1, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals("978-0134685991", book.getIsbn());
        assertEquals(2017, book.getPublishedYear());
        assertEquals(5, book.getTotalCopies());
        assertEquals(3, book.getAvailableCopies());
    }
}
