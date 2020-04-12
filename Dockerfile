FROM ubuntu:16.04
RUN apt-get update -y

FROM openjdk:8-alpine
VOLUME "/tmp"
COPY ./target/AutomobileMaintenanceTracking-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080/tcp
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
