# Etapa de build
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
RUN gradle build -x test

# Etapa de execução
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/SafeZone-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
