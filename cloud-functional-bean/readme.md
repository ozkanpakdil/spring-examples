# Spring Cloud Functional @Bean
See definition at [here](/src/main/java/com/example/demo/DemoApplication.java)

## How to call
start the app with **mvn spring-boot:run** then call it with curl below
```shell
curl -H "Content-Type: text/plain" localhost:8080/test1 -d Hello
```
result is
```shell
HELLO
```