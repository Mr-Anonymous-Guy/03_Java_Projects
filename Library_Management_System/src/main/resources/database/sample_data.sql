USE lms_db;

-- Seed Admin User (username: admin, password: admin123)
INSERT IGNORE INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');

-- Seed Books
INSERT IGNORE INTO books (title, author, isbn, published_year, total_copies, available_copies) VALUES 
('Effective Java', 'Joshua Bloch', '978-0134685991', 2017, 5, 5),
('Clean Code', 'Robert C. Martin', '978-0132350884', 2008, 3, 3),
('The Pragmatic Programmer', 'Andrew Hunt', '978-0135957059', 2019, 4, 4),
('Design Patterns', 'Erich Gamma', '978-0201633610', 1994, 2, 2);

-- Seed Members
INSERT IGNORE INTO members (first_name, last_name, email, phone, join_date) VALUES 
('Alice', 'Johnson', 'alice@example.com', '555-0101', '2023-01-10'),
('Bob', 'Smith', 'bob@example.com', '555-0102', '2023-02-15'),
('Charlie', 'Brown', 'charlie@example.com', '555-0103', '2023-05-20');
