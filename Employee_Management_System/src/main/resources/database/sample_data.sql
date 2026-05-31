USE ems_db;

-- Seed Admin User (username: admin, password: admin123)
-- In a real production system, passwords MUST be hashed!
INSERT IGNORE INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');

-- Seed Departments
INSERT IGNORE INTO departments (name) VALUES ('Human Resources');
INSERT IGNORE INTO departments (name) VALUES ('Engineering');
INSERT IGNORE INTO departments (name) VALUES ('Marketing');
INSERT IGNORE INTO departments (name) VALUES ('Sales');
INSERT IGNORE INTO departments (name) VALUES ('Finance');

-- Seed Employees
INSERT IGNORE INTO employees (first_name, last_name, email, department_id, salary, hire_date) VALUES 
('John', 'Doe', 'john.doe@company.com', 2, 85000.00, '2023-01-15'),
('Jane', 'Smith', 'jane.smith@company.com', 1, 75000.00, '2023-03-01'),
('Michael', 'Johnson', 'michael.j@company.com', 3, 65000.00, '2023-06-10'),
('Emily', 'Davis', 'emily.d@company.com', 2, 95000.00, '2022-11-20'),
('David', 'Wilson', 'david.w@company.com', 4, 72000.00, '2024-01-05');
