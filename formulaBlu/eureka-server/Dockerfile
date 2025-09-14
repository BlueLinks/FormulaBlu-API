# Use a slim JDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY build/libs/eureka-server-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
