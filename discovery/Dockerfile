# Use the official OpenJDK 21 image as a base
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew build.gradle settings.gradle ./
COPY gradle gradle

# Copy the source code
COPY src src

# Install Gradle dependencies
RUN ./gradlew build --no-daemon -x test

# Expose the port that the application will run on
EXPOSE 8761

# Command to run the application in development mode
CMD ["./gradlew", "bootRun"]