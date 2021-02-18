# DateFormatProblem
Before adding the jackson-annotations maven dependency this method (java/rest/DateEndpoint) returned the date with the expected format:

Method reachable under ```http://localhost:8080/DateFormatProblem-1.0-SNAPSHOT/rs/dates/date```

```
@GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate birthDateOwner = LocalDate.parse("1968.03.21", formatter);
        return Response.ok().entity(birthDateOwner).build();
    }
```

Response:

```
"1968-03-21"
```

After adding this dependency (pom.xml) the format of the responded date changed


```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.12.1</version>
</dependency>
```

Response:

```
{"year":1968,"month":"MARCH","chronology":{"id":"ISO","calendarType":"iso8601"},"era":"CE","dayOfMonth":21,"dayOfWeek":"THURSDAY","dayOfYear":81,"leapYear":true,"monthValue":3}
```
