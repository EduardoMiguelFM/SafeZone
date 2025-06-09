# Etapa de build com Gradle e JDK 21
FROM gradle:8.4-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# Etapa de execução com Temurin JDK 21 (leve e confiável)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
