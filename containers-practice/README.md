# Containers Practice

The objective of this practice is to deploy a containerised microservice-based architecture with the following components:

- _The API service_: Which is a REST entry point service in our architecture
- _The employee service_: Which is a REST service behind the _API_
- _MySQL db_: Which will be accessed by the _employee_service_
- _Zipkin server_: For distributing tracing
- _Log analysis?_

Deployment AC's:
- _API service_ must be able to call _employee service_ via HTTP
- _Employee service_ must be connected to _MySQL db_
- _API service_ and _employee service_ must send tracing metrics to the _zipkin server_
- Only _API service_ must be exposed on port 8080 to receive HTTP traffic
- Deploy to azure using container services or AKS

### Start a DB instance

`docker run --name containers-tutorial-mysql-server -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_USER=miztli -e MYSQL_PASSWORD=4321 -e MYSQL_DATABASE=employees-db -p 3306:3306 -d mysql:8.0`

### Start the application

`mvn spring-boot:run`