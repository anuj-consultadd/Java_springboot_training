#  Book Management System

##  Project Description
This is a **Spring Boot** application for managing books in a library. It provides **CRUD operations** for books, including searching by title and author. The backend uses **Spring Boot, JPA, MySQL**, and includes **proper validation and exception handling**.

---

##  Tech Stack
- **Spring Boot** (v3.4.3)
- **Spring Data JPA**
- **MySQL Database**
- **Maven** (for dependency management)
- **Lombok** (for reducing boilerplate code)

---

## 📂 Folder Structure
```
📦 book-management
├── 📂 src/main/java/com/example/bookmanagement
│   ├── 📂 controller        # Handles API requests
│   ├── 📂 dto               # Data Transfer Objects (DTOs)
│   ├── 📂 entity            # JPA Entities
│   ├── 📂 repository        # Data access layer
│   ├── 📂 service           # Business logic
│   ├── 📂 exception         # Custom exceptions (if applicable)
├── 📂 src/main/resources
│   ├── application.properties  # Database & server configuration
├── 📜 pom.xml                 # Maven dependencies
└── 📜 README.md                # Project documentation
```

---

##  Setup Instructions
### Clone the Repository
```sh
git clone https://github.com/anuj-consultadd/Java_springboot_training.git
cd book
```
###  Configure Database
Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
```

###  Build & Run the Application
#### **Using Maven**
```sh
mvn clean install
mvn spring-boot:run
```

#### **Using a JAR File**
```sh
mvn package
java -jar target/library-management-0.0.1-SNAPSHOT.jar
```

---

##  API Endpoints
| Method | Endpoint        | Description |
|--------|----------------|-------------|
| **GET**    | `/api/books`        | Fetch all books |
| **GET**    | `/api/books/{id}`   | Get book by ID |
| **GET**    | `/api/books/search?title={title}` | Search books by title |
| **GET**    | `/api/books/search?author={author}` | Search books by author |
| **POST**   | `/api/books`        | Add a new book |
| **PUT**    | `/api/books/{id}`   | Update book details |
| **PATCH**  | `/api/books/{id}`   | Partially update book details |
| **DELETE** | `/api/books/{id}`   | Delete a book |

---

## 🛠 Common Issues & Fixes
###  **Port 8080 Already in Use**
If you see this error:
```
Web server failed to start. Port 8080 was already in use.
```
Run the following command to stop the process:
#### **Mac/Linux**:
```sh
lsof -i :8080
kill -9 <PID>
```
#### **Windows**:
```sh
netstat -ano | findstr :8080
taskkill /F /PID <PID>
```
Then, restart the application.

###  **Database Connection Issues**
- Ensure MySQL is running.
- Verify your database credentials in `application.properties`.
- If the database doesn't exist, enable `createDatabaseIfNotExist=true` in the connection URL.

---
🔗 **GitHub:** [Repository Link](https://github.com/anuj-consultadd/Java_springboot_training)