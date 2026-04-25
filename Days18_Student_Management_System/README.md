# 🎓 Student Management System (Java + JDBC)

A console-based Student Management System built using Core Java and JDBC.  
It follows a layered architecture with DAO, Service, Model, and Utility classes.

---

## 📌 Features

- Add / Update / Delete Students
- Add Courses and Branches
- Register Students for Courses
- Update Course Fees
- View Students with Course Details
- Search Student by ID
- Cancel Course Registration
- Show High Paying Students
- Course-wise Student Count
- Input Validation (Strong user input handling)

## 🏗️ Project Structure
com.amitesh
│
├── model # POJO classes (Student, Course, Branch, Registration)
├── dao # Database access layer
├── service # Business logic layer
├── menu # Console UI (User interaction)
├── util # DB connection + input handling
└── StudentManagementSystem.java (Main class)

## 🧠 Architecture

The project follows a **3-layer architecture**:

### 1. Presentation Layer
- `StudentManagementMenu`
- Handles user input and menu navigation

### 2. Service Layer
- `StudentService`
- Contains business logic and transaction handling

### 3. Data Access Layer (DAO)
- `StudentDAO`
- `CourseDAO`
- `BranchDAO`
- `RegistrationDAO`
- Handles all database operations using JDBC

## 🗄️ Database Tables

- student (id, name, age, branchid)
- course (courseid, coursename, fee)
- branch (branchid, branchname)
- registration (reg_id, studentid, courseid, feespaid)

## ⚙️ Technologies Used

- Java (Core Java)
- JDBC
- MySQL
- Console-based UI

## 🔌 Database Connection

Configured in:

```java
DBUtil.java
jdbc:mysql://localhost:3306/student_management_system

▶️ How to Run
Create MySQL database student_management_system
Create required tables
Update DB credentials in DBUtil.java
Compile project
Run:
StudentManagementSystem.java

📊 Key Design Highlights
Transaction management for registration & deletion
Input validation utility class
Duplicate registration prevention
Modular DAO structure
Clean separation of concerns


🚀 Future Improvements
Convert to Spring Boot REST API
Add Hibernate/JPA support
Add GUI (JavaFX / Web UI)
Implement authentication system
Add logging framework


👨‍💻 Author
Built by Amitesh

If you want next level upgrade, I can also:
- Convert this into **Spring Boot project structure**
- Add **REST APIs + Swagger**
- Or generate a **professional GitHub portfolio README with badges + screenshots section**

Just tell me 👍