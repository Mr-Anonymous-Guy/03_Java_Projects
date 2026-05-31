# 📚 Library Management System (LMS)

A professional Level-2 Java Desktop Application built with Java Swing, JDBC, MySQL, and Maven.

## 🌟 Features
- **Role-Based Authentication**: Librarian login portal.
- **Book Management**: View and search books.
- **Member Management**: Track library members.
- **Reporting**: Export book lists to CSV natively.
- **UI UX**: Dark mode toggle switch implemented.
- **Database Driven**: Uses pure JDBC connected to a MySQL backend.

## 🛠️ Tech Stack
- **Java 21**: Core language features
- **Java Swing**: Native desktop GUI
- **MySQL 8.x**: Relational Database
- **JDBC**: Database connectivity
- **Maven**: Build and dependency management

## 📁 Repository Structure
```
Library_Management_System/
├── pom.xml                                  # Maven Build File
├── src/
│   └── main/
│       ├── java/
│       │   └── app/
│       │       ├── Main.java                # Entry Point
│       │       ├── model/                   # Domain Models
│       │       ├── dao/                     # Data Access Objects (SQL)
│       │       ├── service/                 # Business Logic
│       │       ├── ui/                      # Swing Components
│       │       └── utils/                   # JDBC Connections
│       └── resources/
│           └── database/
│               ├── schema.sql               # Creates tables
│               └── sample_data.sql          # Dummy initial data
├── run.bat                                  # Windows Execution Script
├── run.sh                                   # Linux/Mac Execution Script
└── README.md
```

## 🏗️ Architecture Design (N-Tier)

The application follows the classic **N-Tier Architecture**:
1. **Presentation Layer (UI)**: Swing Forms (`DashboardFrame`, `LoginFrame`). Calls services.
2. **Business Logic Layer (Service)**: `AuthService`, `LibraryService`. Validates and applies rules.
3. **Data Access Layer (DAO)**: Interfaces directly with `DatabaseConnection` to execute raw queries.
4. **Database Layer (MySQL)**: Persistent storage containing relations and constraints.

## 🚀 Setup & Execution Instructions

### Step 1: Database Setup
1. Ensure you have a running MySQL instance (e.g. via XAMPP).
2. Create the schema and load the data:
   ```sql
   SOURCE src/main/resources/database/schema.sql;
   SOURCE src/main/resources/database/sample_data.sql;
   ```
*(Note: Default JDBC string expects `root` with no password at `localhost:3306`. If yours differs, update `DatabaseConnection.java`)*

### Step 2: Build and Run
This project uses Maven. You do not need to install `javac` manually.

**Windows:**
```cmd
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

### Step 3: Login
- **Username:** admin
- **Password:** admin123
