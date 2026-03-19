# -----------------------------
# Stage 1: Build
# -----------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .

# Download dependencies (cache layer)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the jar (skip tests for faster build)
RUN mvn clean package -DskipTests

# -----------------------------
# Stage 2: Runtime
# -----------------------------
FROM eclipse-temurin:21-jre

# Set working directory inside container
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]