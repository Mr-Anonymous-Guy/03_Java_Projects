package app.service;

import app.dao.DepartmentDAO;
import app.dao.EmployeeDAO;
import app.model.Department;
import app.model.Employee;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
        this.departmentDAO = new DepartmentDAO();
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    public boolean addEmployee(Employee emp) {
        return employeeDAO.addEmployee(emp);
    }

    public boolean updateEmployee(Employee emp) {
        return employeeDAO.updateEmployee(emp);
    }

    public boolean deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    public void exportToCSV(String filePath) throws Exception {
        List<Employee> employees = getAllEmployees();
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("ID,First Name,Last Name,Email,Department,Salary,Hire Date");
            for (Employee e : employees) {
                writer.printf("%d,%s,%s,%s,%s,%.2f,%s\n",
                    e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(),
                    e.getDepartmentName(), e.getSalary(), e.getHireDate().toString());
            }
        }
    }
}
