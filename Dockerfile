# Verwenden Sie ein schlankes Basisimage mit Java 17
FROM openjdk:17-jdk-alpine

# Arbeitsverzeichnis setzen
WORKDIR /app

# Kopieren der Projektdateien
COPY build.gradle settings.gradle
COPY src ./src

# Installieren der Abhängigkeiten
RUN gradle build

# Kopieren der erstellten JAR-Datei
COPY build/libs/*.jar app.jar

# Expositions des Ports (falls benötigt)
EXPOSE 8080

# Starten der Anwendung
ENTRYPOINT ["java","-jar","/app.jar"]