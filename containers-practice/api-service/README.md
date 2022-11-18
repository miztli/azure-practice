### Start the app

`mvn clean spring-boot:run`

### API scripts

```bash
curl --location --request POST 'http://localhost:8080/api/employee' \
--header 'Authorization: Basic YXBpOjRQMQ==' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Miztli",
    "age": 32,
    "gender": "MALE"
}'
```