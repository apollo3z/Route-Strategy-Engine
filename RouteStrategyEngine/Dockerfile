# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

# Add the application's jar to the container
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]