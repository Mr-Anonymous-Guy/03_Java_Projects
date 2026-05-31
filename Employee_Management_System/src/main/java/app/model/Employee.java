package app.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int departmentId;
    private String departmentName; // For display purposes
    private BigDecimal salary;
    private Date hireDate;

    // Constructors, Getters, and Setters
    public Employee() {}

    public Employee(int id, String firstName, String lastName, String email, int departmentId, String departmentName, BigDecimal salary, Date hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
}
