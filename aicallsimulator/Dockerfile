# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy Gradle wrapper files and configuration
COPY gradlew ./
COPY gradle ./gradle/

# Copy project configuration and source code
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src/

# Ensure the gradlew script is executable
RUN chmod +x gradlew

# Build the application, skipping tests for faster build time
RUN ./gradlew clean build -x test

# Expose the port for the Spring Boot application
EXPOSE 8080

# Run the jar file generated by Gradle
CMD ["java", "-jar", "build/libs/aicallsimulator-0.0.1-SNAPSHOT.jar"]
