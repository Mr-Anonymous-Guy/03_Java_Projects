package app.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void testEmployeeCreation() {
        Date hireDate = Date.valueOf("2023-05-15");
        Employee emp = new Employee(1, "John", "Doe", "john@test.com", 2, "IT", new BigDecimal("75000.00"), hireDate);
        
        assertEquals(1, emp.getId());
        assertEquals("John", emp.getFirstName());
        assertEquals("Doe", emp.getLastName());
        assertEquals("john@test.com", emp.getEmail());
        assertEquals(2, emp.getDepartmentId());
        assertEquals("IT", emp.getDepartmentName());
        assertEquals(new BigDecimal("75000.00"), emp.getSalary());
        assertEquals(hireDate, emp.getHireDate());
    }
}
