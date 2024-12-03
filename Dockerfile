# Verwenden Sie das neueste JDK 23 Bild von Eclipse Temurin
FROM eclipse-temurin:17-alpine

# Arbeitsverzeichnis setzen
WORKDIR /app

# Kopiere die Gradle Wrapper Dateien
COPY gradlew gradlew.bat ./

# Installiere Abhängigkeiten
COPY build.gradle settings.gradle ./


# Kopiere die JAR-Datei in das Image
COPY build/libs/Essensplaner-1.0-SNAPSHOT.jar app.jar

# Expose den Port (passe den Port entsprechend deiner Anwendung an)
EXPOSE 8080

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "build/libs/*.jar app.jar"]
