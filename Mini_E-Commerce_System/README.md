# 🛒 Mini E-Commerce System

A robust Level-3 Java Desktop Application simulating a complete e-commerce workflow utilizing Java Swing, JDBC, MySQL, and Maven.

## 🌟 Key Features
- **User Authentication**: Differentiates between `CUSTOMER` and `ADMIN` roles.
- **Product Catalog**: Live loading of products from MySQL.
- **Shopping Cart**: Relational cart system storing user selections.
- **Order & Checkout System**: Transaction-safe (ACID) checkouts that deduct stock, record orders, and clear carts atomically.
- **Payment Simulation**: Records mock payments linked to orders.
- **Dark Mode**: Integrated Swing look-and-feel toggling.

## 🏗️ Architecture Design (N-Tier)
1. **Presentation Layer (UI)**: Swing Forms (`CustomerDashboardFrame`, `AdminDashboardFrame`, `LoginFrame`). Calls services.
2. **Business Logic Layer (Service)**: `AuthService`, `CartService`, `ProductService`. Coordinates DAOs.
3. **Data Access Layer (DAO)**: Interfaces directly with `DatabaseConnection`.
4. **Database Layer (MySQL)**: 8 interlinked tables handling users, products, carts, and orders.

## 🚀 Setup & Execution Instructions

### Step 1: Database Setup
1. Ensure your MySQL instance is running.
2. Load schema and data:
   ```sql
   SOURCE src/main/resources/database/schema.sql;
   SOURCE src/main/resources/database/sample_data.sql;
   ```

### Step 2: Build and Run
Built with Maven.

**Windows:**
```cmd
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
```

### Step 3: Login Accounts
- **Admin**: `admin` / `admin123`
- **Customer**: `john` / `pass123`
