package app.model;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void testMemberCreation() {
        Date joinDate = Date.valueOf("2023-01-10");
        Member member = new Member(1, "Alice", "Johnson", "alice@test.com", "555-0101", joinDate);
        assertEquals(1, member.getId());
        assertEquals("Alice", member.getFirstName());
        assertEquals("Johnson", member.getLastName());
        assertEquals("alice@test.com", member.getEmail());
        assertEquals("555-0101", member.getPhone());
        assertEquals(joinDate, member.getJoinDate());
    }
}
