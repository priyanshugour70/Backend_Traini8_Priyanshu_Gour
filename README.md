# Traini8 - Training Center Registry

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)

## 📋 Overview

Traini8 is a comprehensive registry application for government-funded training centers, enabling efficient management and discovery of training programs across the country. It provides a centralized platform for registering training centers, tracking their offerings, and helping students find appropriate educational opportunities.

## 🌟 Features

### Core Functionality
- **Training Center Management**: CRUD operations for training centers.
- **Advanced Searching & Filtering**: Find centers by location, courses, capacity, etc.
- **Activation Controls**: Activate/deactivate centers without deletion.
- **Unique Code Generation**: Automatic center code generation.
- **Data Validation**: Comprehensive input validation with error feedback.

### Technical Features
- **RESTful API Architecture**: Industry-standard API design.
- **Pagination & Sorting**: Efficient data retrieval.
- **Exception Handling**: Centralized error management.
- **Swagger Documentation**: Interactive API documentation.
- **Logging**: Comprehensive application logging.
- **Database Integration**: JPA/Hibernate with H2 (configurable for other databases).
- **MapStruct & Lombok**: Efficient object mapping & reduced boilerplate code.
- **Unit & Integration Tests**: Comprehensive test coverage.

## 🔧 Technology Stack

**Backend**  
- Java 17  
- Spring Boot 3.4.3  
- Spring Data JPA, Hibernate  
- H2 Database (configurable for MySQL)  
- MapStruct & Lombok  
- SLF4J & Logback  
- SpringDoc OpenAPI (Swagger)

**Development Tools**  
- Maven, Git, Spring DevTools  
- JUnit 5 for testing  

## 🚀 Getting Started

### Prerequisites
- Java 17+  
- Maven 3.8+  
- Git (optional, for cloning)

### Installation & Setup

#### Clone the Repository
```sh
[git clone https://github.com/yourusername/traini8.git](https://github.com/priyanshugour70/Backend_Traini8_Priyanshu_Gour.git)
cd Backend_Traini8_Priyanshu_Gour
```

#### Build the Application
```sh
mvn clean install
```

#### Run the Application
```sh
mvn spring-boot:run
```
The application will start on port `8080` by default.

### Configuration

#### Database Configuration (Default: H2)
Modify `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:traini8db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
#### MySQL Configuration Example
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/traini8db
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
```

#### Server Configuration
```properties
server.port=8080
server.servlet.context-path=/api
```

## 🔍 API Documentation

Once running, access:  
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- **OpenAPI JSON**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

### Key API Endpoints

**Training Center Management**
- `POST /api/v1/training-centers` - Create a new training center
- `GET /api/v1/training-centers` - Get all training centers (paginated)
- `GET /api/v1/training-centers/{id}` - Get a training center by ID
- `PUT /api/v1/training-centers/{id}` - Update a training center
- `DELETE /api/v1/training-centers/{id}` - Delete a training center

**Searching & Filtering**
- `GET /api/v1/training-centers/search?keyword=value` - Search by keyword
- `GET /api/v1/training-centers/city/{city}` - Get centers by city
- `GET /api/v1/training-centers/course/{course}` - Get centers by course

## 🏗 Project Structure

```
traini8/
├── src/
│   ├── main/java/com/traini8/
│   │   ├── config/            # Configuration files
│   │   ├── controller/        # REST Controllers
│   │   ├── dto/               # DTOs for request & response
│   │   ├── entity/            # JPA Entities
│   │   ├── exception/         # Custom exceptions & handlers
│   │   ├── repository/        # Data access layer
│   │   ├── service/           # Business logic layer
│   └── resources/
│       ├── application.properties  # Configuration settings
│       └── logback.xml             # Logging configuration
├── pom.xml                         # Maven dependencies
└── README.md                        # Documentation
```

## 🧪 Testing

Run all tests:
```sh
mvn test
```
Generate coverage report:
```sh
mvn test jacoco:report
```
View report at `target/site/jacoco/index.html`.

## 🔒 Security Considerations

- **Input Validation**: Ensures all user inputs meet validation criteria.  
- **Error Handling**: Sensitive data is not exposed in error responses.  
- **Future Enhancements**: JWT authentication, role-based access, rate limiting.

## 📈 Performance Considerations

- **Indexing**: Key fields (`centerCode`, `city`) indexed for faster queries.  
- **Pagination**: Reduces load by paginating results.  
- **Caching (Planned)**: Redis integration for improved performance.

## 🔄 CI/CD Integration (Planned)

### GitHub Actions Workflow
```yaml
name: Traini8 CI/CD Pipeline
on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn clean verify
      - name: Run tests
        run: mvn test
```

## 🌱 Future Enhancements

1. **User Authentication & Authorization**  
2. **Advanced Analytics** (Dashboard, tracking, reports)  
3. **Mobile Application** (Native iOS & Android support)  
4. **Webhooks & API Integrations**  

## 📄 License

This project is licensed under the **Apache License 2.0**.

## 👥 Team

- **Lead Developer**:   
- **Backend Developer**:   
- **QA Engineer**:   
- **Technical Writer**:   

## 📞 Support

For support, contact:  
📧 Email: support@traini8.gov  
💬 Slack: traini8-support  
🕘 Office Hours: Monday-Friday, 9 AM - 5 PM IST  

---
**Traini8 - Empowering Education, Enabling Growth** 🚀
