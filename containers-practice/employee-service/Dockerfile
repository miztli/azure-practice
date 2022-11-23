FROM openjdk:17-jdk-alpine
MAINTAINER miztli
RUN addgroup -S spring-group && adduser -S spring-app -G spring-group
USER spring-app:spring-group
COPY target/employee-service-0.0.1-SNAPSHOT.jar employee-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/employee-service-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=container"]