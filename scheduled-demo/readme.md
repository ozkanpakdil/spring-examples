# How cron works in spring boot
Basic example of usage of `@Scheduled` check [here](src/main/java/io/github/ozkanpakdil/scheduled_demo/ScheduledJobForSomething.java) 
## how to start
```shell
mvn spring-boot:run
```
### Example log
```shell
C:\Users\ozkan\.jdks\openjdk-21.0.2\bin\java.exe -XX:TieredStopAtLevel=1 -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true "-Dmanagement.endpoints.jmx.exposure.include=*" "-javaagent:C:\Users\ozkan\AppData\Local\Programs\IntelliJ IDEA Ultimate\lib\idea_rt.jar=55601:C:\Users\ozkan\AppData\Local\Programs\IntelliJ IDEA Ultimate\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\ozkan\projects\spring-examples\scheduled-demo\target\classes;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-starter-web\3.3.1\spring-boot-starter-web-3.3.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-starter\3.3.1\spring-boot-starter-3.3.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot\3.3.1\spring-boot-3.3.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\3.3.1\spring-boot-autoconfigure-3.3.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-starter-logging\3.3.1\spring-boot-starter-logging-3.3.1.jar;C:\Users\ozkan\.m2\repository\ch\qos\logback\logback-classic\1.5.6\logback-classic-1.5.6.jar;C:\Users\ozkan\.m2\repository\ch\qos\logback\logback-core\1.5.6\logback-core-1.5.6.jar;C:\Users\ozkan\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.23.1\log4j-to-slf4j-2.23.1.jar;C:\Users\ozkan\.m2\repository\org\apache\logging\log4j\log4j-api\2.23.1\log4j-api-2.23.1.jar;C:\Users\ozkan\.m2\repository\org\slf4j\jul-to-slf4j\2.0.13\jul-to-slf4j-2.0.13.jar;C:\Users\ozkan\.m2\repository\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;C:\Users\ozkan\.m2\repository\org\yaml\snakeyaml\2.2\snakeyaml-2.2.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-starter-json\3.3.1\spring-boot-starter-json-3.3.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.17.1\jackson-databind-2.17.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.17.1\jackson-annotations-2.17.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.17.1\jackson-core-2.17.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.17.1\jackson-datatype-jdk8-2.17.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.17.1\jackson-datatype-jsr310-2.17.1.jar;C:\Users\ozkan\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.17.1\jackson-module-parameter-names-2.17.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\3.3.1\spring-boot-starter-tomcat-3.3.1.jar;C:\Users\ozkan\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\10.1.25\tomcat-embed-core-10.1.25.jar;C:\Users\ozkan\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\10.1.25\tomcat-embed-el-10.1.25.jar;C:\Users\ozkan\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\10.1.25\tomcat-embed-websocket-10.1.25.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-web\6.1.10\spring-web-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-beans\6.1.10\spring-beans-6.1.10.jar;C:\Users\ozkan\.m2\repository\io\micrometer\micrometer-observation\1.13.1\micrometer-observation-1.13.1.jar;C:\Users\ozkan\.m2\repository\io\micrometer\micrometer-commons\1.13.1\micrometer-commons-1.13.1.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-webmvc\6.1.10\spring-webmvc-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-aop\6.1.10\spring-aop-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-context\6.1.10\spring-context-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-expression\6.1.10\spring-expression-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\slf4j\slf4j-api\2.0.13\slf4j-api-2.0.13.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-core\6.1.10\spring-core-6.1.10.jar;C:\Users\ozkan\.m2\repository\org\springframework\spring-jcl\6.1.10\spring-jcl-6.1.10.jar io.github.ozkanpakdil.scheduled_demo.ScheduledDemoApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.1)

2024-07-06T12:36:39.844+01:00  INFO 11952 --- [scheduled-demo] [           main] i.g.o.s.ScheduledDemoApplication         : Starting ScheduledDemoApplication using Java 21.0.2 with PID 11952 (C:\Users\ozkan\projects\spring-examples\scheduled-demo\target\classes started by ozkan in C:\Users\ozkan\projects\spring-examples\scheduled-demo)
2024-07-06T12:36:39.847+01:00  INFO 11952 --- [scheduled-demo] [           main] i.g.o.s.ScheduledDemoApplication         : No active profile set, falling back to 1 default profile: "default"
2024-07-06T12:36:40.565+01:00  INFO 11952 --- [scheduled-demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-07-06T12:36:40.575+01:00  INFO 11952 --- [scheduled-demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-07-06T12:36:40.575+01:00  INFO 11952 --- [scheduled-demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.25]
2024-07-06T12:36:40.612+01:00  INFO 11952 --- [scheduled-demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-07-06T12:36:40.612+01:00  INFO 11952 --- [scheduled-demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 713 ms
2024-07-06T12:36:40.895+01:00  INFO 11952 --- [scheduled-demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-07-06T12:36:40.905+01:00  INFO 11952 --- [scheduled-demo] [           main] i.g.o.s.ScheduledDemoApplication         : Started ScheduledDemoApplication in 1.347 seconds (process running for 1.803)
2024-07-06T12:36:40.908+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:37:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:37:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:37:40.907+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:38:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:38:00.015+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:38:40.910+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:39:00.001+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:39:00.002+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:39:40.918+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:40:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:40:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1-------------------10 minutes:2024-07-06T11:40:00.014431600Z
2024-07-06T12:40:00.022+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:40:00.023+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 2-------------------10 minutes:2024-07-06T11:40:00.023434400Z
2024-07-06T12:40:40.914+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:41:00.009+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:41:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:41:40.903+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:42:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:42:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:42:40.917+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:43:00.005+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:43:00.005+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:43:40.916+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:44:00.004+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:44:00.005+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:44:40.907+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:45:00.010+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:45:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:45:40.911+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:46:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:46:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:46:40.907+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:47:00.010+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:47:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:47:40.912+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:48:00.006+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:48:00.007+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:48:40.909+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:49:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:49:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:49:40.916+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:50:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:50:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:50:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1-------------------10 minutes:2024-07-06T11:50:00.013555600Z
2024-07-06T12:50:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 2-------------------10 minutes:2024-07-06T11:50:00.014555200Z
2024-07-06T12:50:40.917+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:51:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:51:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:51:40.913+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:52:00.004+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:52:00.004+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:52:40.911+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:53:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:53:00.013+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:53:40.905+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:54:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:54:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:54:40.908+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:55:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:55:00.014+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:55:40.918+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:56:00.008+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:56:00.008+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:56:40.918+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:57:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:57:00.004+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:57:40.909+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:58:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:58:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:58:40.909+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T12:59:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T12:59:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T12:59:40.917+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T13:00:00.001+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1-------------------10 minutes:2024-07-06T12:00:00.001305500Z
2024-07-06T13:00:00.002+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T13:00:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 2-------------------10 minutes:2024-07-06T12:00:00.003175400Z
2024-07-06T13:00:00.003+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T13:00:40.904+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T13:01:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T13:01:00.012+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T13:01:40.909+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T13:02:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T13:02:00.011+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute
2024-07-06T13:02:40.915+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : fixed rate 60 seconds
2024-07-06T13:03:00.007+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 60 secs
2024-07-06T13:03:00.008+01:00  INFO 11952 --- [scheduled-demo] [   scheduling-1] i.g.o.s.ScheduledJobForSomething         : 1 minute

```