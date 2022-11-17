
### H2 console

`http://localhost:8080/h2-console`

### API Scripts

```shell
curl --location --request POST 'http://localhost:8080/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Miztli",
    "age": 32,
    "gender": "MALE"
}'
```

### Containerize application

- Create Dockerfile
- Build image: `docker build --tag={image-name}:{version} .`
- Run image in detached mode: `docker run --name employee-service -p8082:8082 -d employee-service:latest`
- To ssh into the container: `docker exec -it {containerId} {bash/sh}`
- To inspect container: `docker inspect {containerId}`
- To stop container: `docker stop {container-id}`
- To remove container: `docker rm {container-id}`

##### Resources
- [Dockerizing a spring-boot app](https://www.baeldung.com/dockerizing-spring-boot-application)