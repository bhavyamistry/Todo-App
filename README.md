# 📝 Todo App – Spring Boot (Java)

A simple and extensible **Todo Management Application** built using **Spring Boot** and **Java**.
This project demonstrates CRUD functionality and DAO/database abstraction by implementing **three different persistence layers**:

1. **In-Memory List**
2. **H2 Database (in-memory)**
3. **MySQL Database (via Docker)**

Each version is implemented in separate commits so you can easily switch between them while learning Spring Boot fundamentals.

---

## 🚀 Features

✔ Create a new Todo
✔ Get all Todos by user - hardcoded, can change as well in package com.bhavyamistry.springboot.myfirstwebapp.security/ SpringSecurity Configuration
✔ Update an existing Todo
✔ Delete a Todo
✔ Layered architecture (Controller → Service → Repository)
✔ Switchable data sources: List / H2 / MySQL
✔ MySQL support via Docker
✔ RESTful API with JSON responses

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot**

  * Spring Web
  * Spring Data JPA
  * Spring Security
  * Spring Validation
  * JQuery
  * Bootstrap
  * JSTL
* **H2 Database**
* **MySQL 8**
* **Maven**
* **Docker**

---

# 📂 Project Structure

```
src/
 ├── main/java/com.bhavyamistry.springboot.myfirstwebapp
 │     ├── /MyFirstWebappApplication.java
 │     ├── hello/
 │     ├── login/
 │     ├── security/
 └── main/resources/
 │     ├── META-INF/resources/WEB-INF/jsp/
 |     └── application.properties
```

---

# 🔀 Available Implementations (Commits)

Use these commits to switch between database approaches:

| Database Type      | Description                            | Commit                                   |
| ------------------ | -------------------------------------- | -----------------------------------------|
| **List-based**     | Todos stored in an in-memory Java List | 74b8aa15e9d64bcfcf7604e9d84b43509c6fd783 |
| **H2 Database**    | Todos stored in H2 using JPA           | 2dabb8ed857132805166e386a5ca1402fbbfa179 |
| **MySQL Database** | Todos stored in MySQL using JPA        | 249f2c44fbf70bb06077359092ac39283f5197fd |


---

# ▶️ Running the Application

## 1️⃣ **Clone the repository**

```bash
git clone [https://github.com/bhavyamistry/Todo-App.git]
cd Todo-App
```
---

# 2️⃣ Running using **In-Memory List**

Nothing to configure.
Just start the project:

```bash
mvn spring-boot:run
```

---

# 3️⃣ Running using **H2 Database**

### Update `application.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.jpa.show-sql=true
```

Run:

```bash
mvn spring-boot:run
```

Access H2 Console:

👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
JDBC URL: `jdbc:h2:mem:testdb`

---

# 4️⃣ Running using **MySQL (Docker)**

## Start MySQL using Docker

Run the following command:

```bash
docker run --detach \
  --env MYSQL_ROOT_PASSWORD=dummypassword \
  --env MYSQL_USER=todos_user \
  --env MYSQL_PASSWORD=dummytodos \
  --env MYSQL_DATABASE=todos \
  --name mysql \
  --publish 3306:3306 \
  mysql:8
```

### Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todos
spring.datasource.username=todos_user
spring.datasource.password=dummytodos

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Start the server:

```bash
mvn spring-boot:run
```

---

# 📡 API Endpoints

| Method   | Endpoint              | Description   |
| -------- | --------------------- | ------------- |
| `GET`    | `/list-todos`         | Get all todos |
| `POST`   | `/add-todo`           | Create todo   |
| `POST`   | `/update-todo?id={id}`| Update todo   |
| `GET`    | `/delete-todo?id={id}`| Delete todo   |

Example request (POST):

```json
{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread"
}
```

---

# 🧪 Testing

You can use **Postman**, **cURL**, or **Insomnia**.

Example cURL:

```bash
curl -X GET http://localhost:8080/list-todos
```

---

# 📘 Notes

* MySQL version may require using root login if custom users fail.
* For `mysql_native_password` support, MySQL 8 standard image already uses compatible authentication.
* If you encounter access denial, remove old Docker volumes:

```bash
docker rm -f mysql
docker volume prune
```

---

# ⭐ Contributions

Feel free to open PRs for:

* pagination support
* authentication (JWT)
* UI frontend
