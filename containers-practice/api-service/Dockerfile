FROM openjdk:17-jdk-alpine
MAINTAINER miztli
RUN addgroup -S spring-group && adduser -S spring-app -G spring-group
USER spring-app:spring-group
COPY target/api-service-0.0.1-SNAPSHOT.jar api-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-service-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=container"]