# Student Course Registration & Management System

An enterprise-grade academic Mini Project designed to demonstrate the fundamentals of core Java Web (J2EE) architecture. The application implements a complete multi-tier Model-View-Controller (MVC) decoupling layout using Java Servlets, JSP, JDBC, Session guards, Cookie tracking matrices, and structured custom exception boundary layers.

## 🚀 Key Architectural Pillars

- **Strict MVC Decoupling**: View scopes (`JSP`) are secured behind `/WEB-INF/views/` to completely block client-side file bypassing. All operations pass securely through controllers (`Servlets`) first.
- **Defensive Error Validation Boundary**: Native engine database constraints are verified prior to relational execution via an application layer constraint checker (`DAOException`). Prevents raw database crashes (e.g. unique course_name collisions) from compromising thread states.
- **Robust Connection Lifecycle Management**: Implements Java's `try-with-resources` construct on all JDBC `Connection`, `PreparedStatement`, and `ResultSet` components to completely prevent unclosed cursor leakages or database connection pool starvation.
- **Secure Stateful Workflows**: Utilizes standard session attributes (`loggedInUser`) to guard restricted areas and deploys stateful client cookies to manage "Remember Me" utilities securely without saving plain text credentials locally.

---

## 🛠️ Technology Stack

- **Recommended IDE**: Eclipse Enterprise Edition (Java EE)
- **Application Server**: Apache Tomcat 9.0+ / 10.0+
- **Database Engine**: MySQL Server 8.0+
- **Backend Components**: Java Servlets (Jakarta/javax.servlet execution specs)
- **View Pipeline**: JavaServer Pages (JSP), Scriptlets, Standard Expressions
- **Driver Connectivity**: JDBC (`mysql-connector-j-8.0.31.jar`)

---

## 📂 System Package Map


src/main/java
└── com.studentcourse
    ├── controller    # Servlets executing navigation routing & form capturing
    ├── dao           # Encapsulated transactional SQL and data maps
    ├── exception     # Custom business logic exception boundary definitions
    ├── model         # Domain entities and transactional POJOs
    └── util          # Shared relational context connection utilities


---

 👨‍💻 Author
Built by Amitesh