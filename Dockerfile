# Use an official Maven image to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight JDK 17 runtime image for running the application
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/project-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default Spring Boot port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
