## Сборка приложения
#FROM maven:3.8.6-openjdk-21 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests
#
# Финальный образ
FROM openjdk:21-jdk-slim
WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
COPY target/engBoost-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
