FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build.gradle settings.gradle gradle.properties ./
COPY src ./src

RUN apk add gradle

RUN gradle build

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]