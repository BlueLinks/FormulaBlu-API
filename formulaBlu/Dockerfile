# Use a slim JDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY race/build/libs/race-1.0-SNAPSHOT-plain.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]