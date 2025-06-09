FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/SafeZone-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
