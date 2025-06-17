# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files (pom.xml) first to leverage Docker cache
COPY pom.xml .
# Copy the source code (including mvnw if you still have it, though it won't be used now)
COPY src ./src

# --- NEW ADDITION: Install Maven ---
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# --- MODIFIED: Build the Spring Boot application using the installed Maven ---
RUN mvn clean install -DskipTests

# Use a smaller base image for the final stage to reduce image size
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/vehicle-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
