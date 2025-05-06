# ICEB Library Application

This is a Spring Boot-based library application that provides functionalities for managing books and locations. The application uses PostgreSQL as the database.

## Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/tech-bosque/iceb-library-api.git
    cd iceb-library-api
    ```

2. **Build the project**:
    ```sh
    mvn clean install
    ```

3. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

4. **Package the application**:
   Run the following command to create the JAR file in the `target` folder. This is necessary for the Dockerfile to work correctly:
   ```sh
   mvn clean package
   ```

5. **Run with Docker**:
   Ensure you have Docker installed. Use the provided `docker-compose.yml` to build and run the application and the database. Make sure that the `target` folder contains the JAR file of the project before running this command:
    ```sh
    docker-compose up --build
    ```

## API Documentation
The application uses OpenAPI 3.0 for API documentation. You can access the API documentation at:
```ini
http://localhost:8080/swagger-ui.html
```
You can check the endpoints by accessing the Swagger page:
```ini
http://localhost:8080/swagger-ui/index.html#/
```
