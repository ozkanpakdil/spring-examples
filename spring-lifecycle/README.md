#Spring boot life cycle events
When a spring boot application starts there are ways to catch if the application started. 

You can see an example start log down below and which class comes to scene.

```  .   ____          _            __ _ _
    /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
   ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
    \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
     '  |____| .__|_| |_|_| |_\__, | / / / /
    =========|_|==============|___/=/_/_/_/
    :: Spring Boot ::        (v2.2.0.RELEASE)
   
   2019-11-06 21:59:12.657  INFO 11692 --- [           main] c.m.s.SpringLifecycleApplication         : Starting SpringLifecycleApplication on DESKTOP-NF90OD6 with PID 11692 (D:\Projects\spring-examples\spring-lifecycle\target\classes started by ozkan in D:\Projects\spring-examples\spring-lifecycle)
   2019-11-06 21:59:12.660  INFO 11692 --- [           main] c.m.s.SpringLifecycleApplication         : No active profile set, falling back to default profiles: default
   2019-11-06 21:59:13.531  INFO 11692 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
   2019-11-06 21:59:13.539  INFO 11692 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
   2019-11-06 21:59:13.539  INFO 11692 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
   2019-11-06 21:59:13.607  INFO 11692 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
   2019-11-06 21:59:13.607  INFO 11692 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 911 ms
   2019-11-06 21:59:13.645  INFO 11692 --- [           main] c.m.s.AllStrategiesExampleBean           : Constructor
   2019-11-06 21:59:13.646  INFO 11692 --- [           main] c.m.s.AllStrategiesExampleBean           : PostConstruct
   2019-11-06 21:59:13.646  INFO 11692 --- [           main] c.m.s.AllStrategiesExampleBean           : InitializingBean
   2019-11-06 21:59:13.721  INFO 11692 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
   2019-11-06 21:59:13.818  INFO 11692 --- [           main] c.m.s.EventListenerExampleBean           : org.springframework.context.event.ContextRefreshedEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@776aec5c, started on Wed Nov 06 21:59:12 CET 2019]
   2019-11-06 21:59:13.822  INFO 11692 --- [           main] c.m.springlifecycle.AppStartupListener   : org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@776aec5c, started on Wed Nov 06 21:59:12 CET 2019
   2019-11-06 21:59:13.822  INFO 11692 --- [           main] c.m.s.StartupApplicationListenerExample  : org.springframework.context.event.ContextRefreshedEvent[source=org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@776aec5c, started on Wed Nov 06 21:59:12 CET 2019]
   2019-11-06 21:59:14.026  INFO 11692 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
   2019-11-06 21:59:14.026  INFO 11692 --- [           main] c.m.springlifecycle.AppStartupListener   : org.springframework.boot.web.embedded.tomcat.TomcatWebServer@351f2244
   2019-11-06 21:59:14.029  INFO 11692 --- [           main] c.m.s.SpringLifecycleApplication         : Started SpringLifecycleApplication in 1.656 seconds (JVM running for 1.933)
   2019-11-06 21:59:14.029  INFO 11692 --- [           main] c.m.springlifecycle.AppStartupListener   : org.springframework.boot.SpringApplication@459cfcca
   2019-11-06 21:59:14.030  INFO 11692 --- [           main] c.m.springlifecycle.AppStartupRunner     : Application started with option names : []
   2019-11-06 21:59:14.031  INFO 11692 --- [           main] c.m.s.CommandLineAppStartupRunner        : []
   2019-11-06 21:59:14.031  INFO 11692 --- [           main] c.m.springlifecycle.AppStartupListener   : org.springframework.boot.SpringApplication@459cfcca
```