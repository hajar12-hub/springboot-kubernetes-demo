# Étape 1 : Build de l'application
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src

# Compiler et créer le JAR
RUN mvn clean package -DskipTests

# Étape 2 : Image légère pour l'exécution
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copier le JAR généré
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port utilisé par Spring Boot
EXPOSE 8081

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]