package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void testEmployeeCreation() {
        Employee emp = new Employee(1, "John", "Doe", "john@test.com", "IT", 75000);
        assertEquals(1, emp.getId());
        assertEquals("John", emp.getFirstName());
        assertEquals("Doe", emp.getLastName());
        assertEquals("john@test.com", emp.getEmail());
        assertEquals("IT", emp.getDepartment());
        assertEquals(75000, emp.getSalary());
    }
}
