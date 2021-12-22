## test manually
```shell
mvn spring-boot:run
```
and call curl like below
```shell
$ curl 'localhost:8080/demo/user/native/setsession/true1'
true
$ curl 'localhost:8080/demo/user/native/getsession/'
true1
$ curl 'localhost:8080/demo/user/native/setsession/true2'
true
$ curl 'localhost:8080/demo/user/native/getsession/'
true2
```
## how to run tests
```shell
mvn test
```
here is example output
```shell
2021-12-22 18:42:35.966  INFO 1047667 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
Hibernate: 
    
    create table user (
       id integer not null,
        email varchar(255),
        name varchar(255),
        primary key (id)
    )
Hibernate: create sequence hibernate_sequence start with 1 increment by 1
2021-12-22 18:42:36.761  INFO 1047667 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2021-12-22 18:42:36.815  INFO 1047667 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2021-12-22 18:42:37.694  WARN 1047667 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2021-12-22 18:42:38.522  INFO 1047667 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2021-12-22 18:42:38.522  INFO 1047667 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2021-12-22 18:42:38.524  INFO 1047667 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2021-12-22 18:42:38.557  INFO 1047667 --- [           main] c.m.m.MysqlSessionApplicationTests       : Started MysqlSessionApplicationTests in 5.798 seconds (JVM running for 7.547)
Hibernate: 
    
set
    @FLAG=?

MockHttpServletRequest:
      HTTP Method = GET
      Request URI = /demo/user/native/setsession/sesval
       Parameters = {}
          Headers = []
             Body = null
    Session Attrs = {}

Handler:
             Type = com.mascix.mysqlsession.MainController
           Method = com.mascix.mysqlsession.MainController#setSession(String)

Async:
    Async started = false
     Async result = null

Resolved Exception:
             Type = null

ModelAndView:
        View name = null
             View = null
            Model = null

FlashMap:
       Attributes = null

MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = [Content-Type:"application/json"]
     Content type = application/json
             Body = true
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
Hibernate: 
    select
        @FLAG

MockHttpServletRequest:
      HTTP Method = GET
      Request URI = /demo/user/native/getsession/
       Parameters = {}
          Headers = []
             Body = null
    Session Attrs = {}

Handler:
             Type = com.mascix.mysqlsession.MainController
           Method = com.mascix.mysqlsession.MainController#getSession()

Async:
    Async started = false
     Async result = null

Resolved Exception:
             Type = null

ModelAndView:
        View name = null
             View = null
            Model = null

FlashMap:
       Attributes = null

MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"6"]
     Content type = text/plain;charset=UTF-8
             Body = sesval
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
Hibernate: 
    
set
    @FLAG=?

MockHttpServletRequest:
      HTTP Method = GET
      Request URI = /demo/user/native/setsession/sesval
       Parameters = {}
          Headers = []
             Body = null
    Session Attrs = {}

Handler:
             Type = com.mascix.mysqlsession.MainController
           Method = com.mascix.mysqlsession.MainController#setSession(String)

Async:
    Async started = false
     Async result = null

Resolved Exception:
             Type = null

ModelAndView:
        View name = null
             View = null
            Model = null

FlashMap:
       Attributes = null

MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = [Content-Type:"application/json"]
     Content type = application/json
             Body = true
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.099 s - in com.mascix.mysqlsession.MysqlSessionApplicationTests
2021-12-22 18:42:39.049  INFO 1047667 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2021-12-22 18:42:39.053  INFO 1047667 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2021-12-22 18:42:39.068  INFO 1047667 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.928 s
[INFO] Finished at: 2021-12-22T18:42:39Z
[INFO] ------------------------------------------------------------------------

```