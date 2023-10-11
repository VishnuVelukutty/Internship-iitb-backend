FROM openjdk:17-alpine
WORKDIR /backend
COPY project-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "project-0.0.1-SNAPSHOT.jar"]