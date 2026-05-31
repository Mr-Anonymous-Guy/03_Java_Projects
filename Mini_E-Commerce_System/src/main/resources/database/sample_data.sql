USE ecommerce_db;

-- Seed Users
INSERT IGNORE INTO users (username, password, email, role) VALUES 
('admin', 'admin123', 'admin@ecommerce.com', 'ADMIN'),
('john', 'pass123', 'john@example.com', 'CUSTOMER'),
('jane', 'pass123', 'jane@example.com', 'CUSTOMER');

-- Create cart for john and jane
INSERT IGNORE INTO carts (user_id) SELECT id FROM users WHERE role = 'CUSTOMER';

-- Seed Categories
INSERT IGNORE INTO categories (name, description) VALUES 
('Electronics', 'Gadgets and electronic devices'),
('Clothing', 'Apparel and accessories'),
('Books', 'Physical and digital books');

-- Seed Products
INSERT IGNORE INTO products (category_id, name, description, price, stock) VALUES 
(1, 'Smartphone X', 'Latest model with 256GB storage', 999.99, 50),
(1, 'Laptop Pro', '16-inch display, 16GB RAM', 1499.50, 30),
(1, 'Wireless Earbuds', 'Noise cancelling, 20h battery', 129.99, 100),
(2, 'Cotton T-Shirt', '100% organic cotton', 19.99, 200),
(2, 'Running Shoes', 'Lightweight athletic shoes', 89.99, 75),
(3, 'Java Programming', 'Comprehensive guide to Java 21', 45.00, 40),
(3, 'Clean Architecture', 'Software structure and design', 35.00, 25);
