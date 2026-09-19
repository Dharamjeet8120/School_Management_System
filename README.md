# School Management System

A full-stack School Management System built with **Spring Boot** (backend) and **React.js** (frontend), featuring role-based security and a clean layered architecture to manage students, teachers, exams, fees, and results.

---

## 🚀 Features

- **Multi-Role Access** – Separate roles for **Admin**, **Teacher**, and **Accounts**, each with restricted, role-based views and permissions
- **Student & Teacher Management** – Manage core records for students, teachers, and classes
- **Exam & Results Management** – Handle exam schedules and record/view results
- **Fee Management** – Track and manage student fee records
- **Secure Configuration** – Sensitive credentials (like DB passwords) kept out of source code via environment variables
- **Consistent API Responses** – Centralized exception handling ensures reliable, predictable error responses
- **Clean Data Contracts** – DTO/Mapper pattern keeps API responses clean and avoids entity serialization issues

---

## 🛠️ Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Security
- Hibernate / JPA
- MySQL
- Maven

**Frontend**
- React.js
- Axios (API integration)

**Tools**
- Git & GitHub
- Postman (API testing)

---

## 🏗️ Architecture

The backend follows a clean **3-layer architecture**:

```
Controller  →  Service  →  Repository  →  Database
```

Key design choices:
- **DTO/Mapper Pattern** – Entities are never exposed directly through the API; DTOs prevent serialization issues (like recursive relationships) and keep responses clean
- **Centralized Exception Handling** – A global `@RestControllerAdvice` handles errors consistently across all endpoints
- **Bean Validation** – Input is validated at the DTO layer before reaching business logic
- **Environment-based Configuration** – Database credentials are externalized into environment variables rather than hardcoded in `application.properties`

---

## 📦 Core Entities

| Entity     | Description                                   |
|------------|------------------------------------------------|
| Student    | Student profile and enrollment details          |
| Teacher    | Teacher profile and assigned classes            |
| Class      | Class/section information                       |
| Subject    | Subjects taught per class                        |
| Exam       | Exam schedules and details                       |
| Fee        | Student fee records and payment tracking         |
| Result     | Exam results linked to students                  |

---

## 🔐 Security & Roles

Spring Security enforces **role-based access control** across the application:

| Role      | Access                                                  |
|-----------|-----------------------------------------------------------|
| ADMIN     | Full access — manage students, teachers, classes, fees   |
| TEACHER   | Manage exams, enter/view results for assigned classes     |
| ACCOUNTS  | Manage and view fee records                                |

> ℹ️ Update this table if your actual role permissions differ.

---

## 📡 Sample API Endpoints

| Method | Endpoint              | Role Required      | Description                    |
|--------|-------------------------|---------------------|----------------------------------|
| POST   | `/api/students`         | ADMIN               | Add a new student               |
| GET    | `/api/students`         | ADMIN, TEACHER      | List all students               |
| POST   | `/api/exams`            | ADMIN, TEACHER      | Schedule a new exam             |
| POST   | `/api/results`          | TEACHER             | Submit exam results             |
| GET    | `/api/fees/{studentId}` | ADMIN, ACCOUNTS     | View a student's fee record     |

> ℹ️ Update this table to match your actual endpoint paths before publishing.

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+ (or your configured JDK version)
- Maven
- MySQL
- Node.js & npm (for the React frontend)

### Backend Setup
```bash
# Clone the repository
git clone https://github.com/Dharamjeet8120/School_Management_System.git
cd School_Management_System

# Set environment variables for DB credentials
# export DB_URL=jdbc:mysql://localhost:3306/school_db
# export DB_USERNAME=your_username
# export DB_PASSWORD=your_password

# Run the application
mvn spring-boot:run
```

### Frontend Setup
```bash
cd frontend
npm install
npm start
```

The backend runs by default on `http://localhost:8080` and the frontend on `http://localhost:3000` (adjust as per your configuration).

---

## 📸 Screenshots

> Add login, dashboard, and role-specific view screenshots here to give recruiters a quick visual preview.

---

## 👤 Author

**Dharamjeet Kushwaha**
- GitHub: [@Dharamjeet8120](https://github.com/Dharamjeet8120)
- LinkedIn: [Dharamjeet Kushwaha](https://www.linkedin.com/in/dharamjeetkushwaha-9592081ba)
- Portfolio: [dharamjeet8120.github.io/my_portfolio_website](https://dharamjeet8120.github.io/my_portfolio_website/)

---

## 📄 License

This project is open for educational and portfolio purposes.
