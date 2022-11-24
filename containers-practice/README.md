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

### Test compose.yml

`docker-compose config`

### Start the environment with docker compose

`docker compose up -d --build`

Inspect health-check logs: `docker inspect --format "{{json .State.Health }}" container-name`

### Stop the environment with docker compose

`docker-compose down`

### [Deploying app to azure cloud](azure-docs.md)