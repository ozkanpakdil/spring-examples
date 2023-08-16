# How to run the app
From bash
```
./gradlew bootRun
```
and then you can go to h2 console from http://localhost:8080/h2-console

driver: "org.sqlite.JDBC"

jdbc url: "jdbc:sqlite:file:myDb.db?cache=shared"

username password "root"


check https://stackoverflow.com/questions/76894127/using-flyway-with-sqlite-database-in-spring-boot/76917474#76917474 for screen shots
