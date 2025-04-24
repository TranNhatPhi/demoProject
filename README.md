# demoProject

## Technology Stack
- **Java**: JDK 17
- **Spring Boot**: 3.4.4
- **Database**: MySQL 8
- **Build Tool**: Maven
- **JPA / Hibernate**: for ORM
- **Lombok**: for reducing boilerplate code

*(Assuming your server runs on port 8080)*

---

### 🧵 API Endpoints

| Method | Endpoint           | Description             |
|--------|--------------------|-------------------------|
| GET    | `/hello`            | Test hello API          |
| POST   | `/`                 | Create new item         |
| GET    | `/`                 | Get all items           |
| GET    | `/{id}`             | Get item by ID          |
| PUT    | `/{id}`             | Update item by ID       |
| DELETE | `/{id}`             | Delete item by ID       |

---

### 📬 Example API Calls

#### ➡️ 1. Test Hello API

- **Request**:
```bash
GET http://localhost:8080/demo/hello
```
{
  "status": 200,
  "message": "demo controller",
  "data": "hello"
}