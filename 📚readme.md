
 Journal App - Spring Boot Learning Project

A REST API application for managing users and their journal entries, built with Spring Boot and MongoDB.

## 🛠️ Technologies Used

- **Spring Boot 3.4.7**
- **MongoDB**
- **Spring Data MongoDB**
- **Lombok**
- **Maven**
- **Java 17**


## Features

-  User management (Create, Read, Update)
-  Journal entry CRUD operations
-  MongoDB integration with document references
-  Transaction support for data consistency
-  RESTful API design
-  Health check endpoint
-  Automatic JSON serialization/deserialization


##  API Endpoints

### Health Check

- `GET /health-check` - Application status


### User Management (`/user`)

| Method | Endpoint | Description |
| :-- | :-- | :-- |
| GET | `/user` | Get all users |
| POST | `/user` | Create new user |
| PUT | `/user/{username}` | Update existing user |

### Journal Management (`/journal`)

| Method | Endpoint | Description |
| :-- | :-- | :-- |
| GET | `/journal/{userName}` | Get user's journal entries |
| POST | `/journal/{userName}` | Create new journal entry |
| GET | `/journal/id/{entryId}` | Get specific entry by ID |
| PUT | `/journal/id/{userName}/{entryId}` | Update existing entry |
| DELETE | `/journal/id/{userName}/{entryId}` | Delete entry |

##  How to Run

### Prerequisites

- Java 17 or higher
- MongoDB running on `localhost:27017`
- Maven 3.6+


### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/journal-app-spring-boot.git
cd journal-app-spring-boot
```

2. **Start MongoDB**
```bash
# Make sure MongoDB is running on localhost:27017
mongod
```

3. **Run the application**
```bash
./mvnw spring-boot:run
# OR
mvn spring-boot:run
```

4. **Verify it's working**
```bash
curl http://localhost:8081/health-check
# Should return: "OK"
```


## 📱 API Usage Examples

### Create a User

```bash
curl -X POST http://localhost:8081/user \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john123",
    "password": "mypassword"
  }'
```


### Create Journal Entry

```bash
curl -X POST http://localhost:8081/journal/john123 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My Great Day",
    "content": "Today was an amazing day! I learned so much about Spring Boot."
  }'
```


### Get User's Entries

```bash
curl http://localhost:8081/journal/john123
```


### Update Journal Entry

```bash
curl -X PUT http://localhost:8081/journal/id/john123/ENTRY_ID \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Title",
    "content": "Updated content here..."
  }'
```


## 🗄️ Database Schema

### MongoDB Collections

**Users Collection (`user`)**

```json
{
  "_id": ObjectId("..."),
  "username": "john123",
  "password": "mypassword",
  "journalEntries": [
    {"$ref": "journal_entries", "$id": ObjectId("...")}
  ]
}
```

**Journal Entries Collection (`journal_entries`)**

```json
{
  "_id": ObjectId("..."),
  "title": "My Great Day",
  "content": "Today was amazing!",
  "date": "2025-08-05T14:30:00.000Z"
}
```


## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/debOpsjapp/
│   │   ├── journalAppl.java              # Main application class
│   │   ├── controller/
│   │   │   ├── JournalEntryControllerV2.java
│   │   │   ├── UserController.java
│   │   │   └── HealthCheck.java
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   └── JournalEntry.java
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   └── JournalEntryService.java
│   │   └── repo/
│   │       ├── UserRepo.java
│   │       └── JournalEntryRepo.java
│   └── resources/
│       └── application.properties
└── test/
```

## ⚙️ Configuration

### Application Properties

```properties
spring.application.name=JOurApp
server.port=8081
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=journalDB
spring.data.mongodb.auto-index-creation=true
```


##  Key Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

##  License

This project is open source and available under the [MIT License](LICENSE).

##  About This Project

Built as a hands-on learning project to understand Spring Boot fundamentals, REST API development, and MongoDB integration. Perfect for beginners learning modern Java web development!

If this project helped you learn Spring Boot, please give it a star!

