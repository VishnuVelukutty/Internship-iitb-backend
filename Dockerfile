FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /backend
COPY /project /backend
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /backend
COPY --from=builder /backend/target/ .
EXPOSE 8080
CMD ["java", "-jar", "backend.jar"]


# Alternatively you can copy the jar file if you have that
# FROM openjdk:17-alpine
# WORKDIR /backend
# COPY backend.jar .
# EXPOSE 8080
# CMD ["java", "-jar", "backend.jar"]