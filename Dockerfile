

# Stage 1: Build the application
FROM maven:3-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .
# Copy your source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-alpine

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]

