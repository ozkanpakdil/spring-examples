# How to run

`./mvnw spring-boot:run`

# how to build native image and start it

`./mvnw -ntp package native:compile`

then

For windows `set TEST_VAR1=123`
For linux `export TEST_VAR1=123`

then

`./target/demo.exe`

# how to test

curl -s http://localhost:8080/

user should see what ever env variable set for TEST_VAR1

and result should be like below
```shell
ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ curl -s http://localhost:8080/
123
ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$
```

example output from windows git bash
```shell
ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ ./mvnw -ntp package native:compile
MINGW support requires --add-opens java.base/java.lang=ALL-UNNAMED
MINGW support requires --add-opens java.base/java.lang=ALL-UNNAMED
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------------< com.example:demo >--------------------------
[INFO] Building demo 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ demo ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ demo ---
[INFO] No sources to compile
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ demo ---
[WARNING]  Parameter 'systemProperties' is deprecated: Use systemPropertyVariables instead.
[INFO] No tests to run.
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ demo ---
[INFO] Building jar: C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.3.3:repackage (repackage) @ demo ---
[INFO] Replacing main artifact C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] >>> native:0.10.2:compile (default-cli) > package @ demo >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ demo ---
[INFO] Nothing to compile - all classes are up to date.
[WARNING] Overwriting artifact's file from C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar to C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ demo ---
[INFO] No sources to compile
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ demo ---
[WARNING]  Parameter 'systemProperties' is deprecated: Use systemPropertyVariables instead.
[INFO] No tests to run.
[INFO] Skipping execution of surefire because it has already been run for this configuration
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ demo ---
[INFO]
[INFO] --- spring-boot:3.3.3:repackage (repackage) @ demo ---
[INFO] Replacing main artifact C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] <<< native:0.10.2:compile (default-cli) < package @ demo <<<
[INFO]
[INFO]
[INFO] --- native:0.10.2:compile (default-cli) @ demo ---
[INFO] Found GraalVM installation from GRAALVM_HOME variable.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO] Executing: C:\sdkman\candidates\java\21.0.2-graal\bin\native-image.cmd @target\tmp\native-image-9183821623111949596.args com.example.demo.DemoApplication
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-core/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-core/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-websocket/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-el/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-el/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-websocket/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
========================================================================================================================
GraalVM Native Image: Generating 'demo' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------
[1/8] Initializing...                                                                                   (15.8s @ 0.15GB)
 Java version: 21.0.2+13-LTS, vendor version: Oracle GraalVM 21.0.2+13.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3, PGO: ML-inferred
 C compiler: cl.exe (microsoft, x64, 19.41.34120)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 2 user-specific feature(s):
 - com.oracle.svm.thirdparty.gson.GsonFeature
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
------------------------------------------------------------------------------------------------------------------------
 2 experimental option(s) unlocked:
 - '-H:ResourceConfigurationResources' (origin(s): 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-core\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.28/tomcat-embed-core-10.1.28.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-el\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.28/tomcat-embed-el-10.1.28.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-websocket\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.28/tomcat-embed-websocket-10.1.28.jar')
 - '-H:ReflectionConfigurationResources' (origin(s): 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-core\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.28/tomcat-embed-core-10.1.28.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-el\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.28/tomcat-embed-el-10.1.28.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-websocket\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.28/tomcat-embed-websocket-10.1.28.jar')
------------------------------------------------------------------------------------------------------------------------
Build resources:
 - 11.92GB of memory (75.6% of 15.77GB system memory, determined at start)
 - 8 thread(s) (100.0% of 8 available processor(s), determined at start)
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
[2/8] Performing analysis...  [*****]                                                                   (89.7s @ 2.18GB)
   16,847 reachable types   (90.1% of   18,695 total)
   26,129 reachable fields  (64.8% of   40,342 total)
   88,448 reachable methods (64.7% of  136,601 total)
    5,438 types,   446 fields, and 6,293 methods registered for reflection
       78 types,    62 fields, and    68 methods registered for JNI access
        5 native libraries: crypt32, ncrypt, psapi, version, winhttp
[3/8] Building universe...                                                                              (13.6s @ 2.03GB)
[4/8] Parsing methods...      [******]                                                                  (37.2s @ 2.70GB)
[5/8] Inlining methods...     [****]                                                                     (7.5s @ 3.29GB)





[6/8] Compiling methods...    [*********************]                                                  (478.9s @ 1.54GB)
[7/8] Layouting methods...    [****]                                                                    (18.5s @ 2.63GB)
[8/8] Creating image...       [*****]                                                                   (30.4s @ 3.30GB)
  46.68MB (57.02%) for code area:    51,434 compilation units
  34.55MB (42.20%) for image heap:  418,740 objects and 268 resources
 654.73kB ( 0.78%) for other data
  81.88MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  16.17MB java.base                                           12.94MB byte[] for code metadata
   5.19MB tomcat-embed-core-10.1.28.jar                        5.86MB byte[] for java.lang.String
   4.99MB svm.jar (Native Image)                               3.03MB java.lang.String
   4.16MB java.xml                                             2.99MB java.lang.Class
   2.41MB jackson-databind-2.17.2.jar                          1.39MB byte[] for embedded resources
   1.96MB spring-core-6.1.12.jar                               1.11MB byte[] for reflection metadata
   1.82MB spring-boot-3.3.3.jar                              820.49kB byte[] for general heap data
 905.25kB spring-beans-6.1.12.jar                            789.70kB com.oracle.svm.core.hub.DynamicHubCompanion
 791.89kB spring-web-6.1.12.jar                              561.23kB heap alignment
 790.45kB logback-core-1.5.7.jar                             483.09kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
   7.20MB for 72 more packages                                 4.65MB for 3560 more object types
                              Use '-H:+BuildReport' to create a report with more details.
------------------------------------------------------------------------------------------------------------------------
Security report:
 - Binary includes Java deserialization.
 - Use '--enable-sbom' to embed a Software Bill of Materials (SBOM) in the binary.
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 PGO:  Use Profile-Guided Optimizations ('--pgo') for improved throughput.
 INIT: Adopt '--strict-image-heap' to prepare for the next GraalVM release.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
 QBM:  Use the quick build mode ('-Ob') to speed up builds during development.
------------------------------------------------------------------------------------------------------------------------
                       37.6s (5.4% of total time) in 205 GCs | Peak RSS: 5.16GB | CPU load: 4.10
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo.exe (executable)
========================================================================================================================
Finished generating 'demo' in 11m 34s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11:41 min
[INFO] Finished at: 2024-09-01T01:46:41+01:00
[INFO] ------------------------------------------------------------------------

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ ./target/demo.exe

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.3)

2024-09-01T11:05:28.970+01:00  INFO 11548 --- [demo] [           main] com.example.demo.DemoApplication         : Starting AOT-processed DemoApplication using Java 21.0.2 with PID 11548 (C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo.exe started by ozkan in C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables)
2024-09-01T11:05:28.970+01:00  INFO 11548 --- [demo] [           main] com.example.demo.DemoApplication         : No active profile set, falling back to 1 default profile: "default"
2024-09-01T11:05:28.994+01:00  INFO 11548 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-09-01T11:05:28.995+01:00  INFO 11548 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-09-01T11:05:28.995+01:00  INFO 11548 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.28]
2024-09-01T11:05:29.003+01:00  INFO 11548 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-09-01T11:05:29.003+01:00  INFO 11548 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 32 ms
2024-09-01T11:05:29.006+01:00  WARN 11548 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'appConfig': Instantiation of supplied bean failed
2024-09-01T11:05:29.006+01:00  INFO 11548 --- [demo] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
Application run failed
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'appConfig': Instantiation of supplied bean failed
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainFromSupplier(AbstractAutowireCapableBeanFactory.java:1243)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1180)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:971)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:625)
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:335)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352)
        at com.example.demo.DemoApplication.main(DemoApplication.java:10)
        at java.base@21.0.2/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)
Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'TEST_VAR1' in value "${TEST_VAR1}"
        at org.springframework.util.PropertyPlaceholderHelper.parseStringValue(PropertyPlaceholderHelper.java:180)
        at org.springframework.util.PropertyPlaceholderHelper.replacePlaceholders(PropertyPlaceholderHelper.java:126)
        at org.springframework.core.env.AbstractPropertyResolver.doResolvePlaceholders(AbstractPropertyResolver.java:239)
        at org.springframework.core.env.AbstractPropertyResolver.resolveRequiredPlaceholders(AbstractPropertyResolver.java:210)
        at org.springframework.core.env.AbstractPropertyResolver.resolveNestedPlaceholders(AbstractPropertyResolver.java:230)
        at org.springframework.boot.context.properties.source.ConfigurationPropertySourcesPropertyResolver.getProperty(ConfigurationPropertySourcesPropertyResolver.java:80)
        at org.springframework.boot.context.properties.source.ConfigurationPropertySourcesPropertyResolver.getProperty(ConfigurationPropertySourcesPropertyResolver.java:61)
        at org.springframework.core.env.AbstractEnvironment.getProperty(AbstractEnvironment.java:552)
        at org.springframework.context.support.PropertySourcesPlaceholderConfigurer$1.getProperty(PropertySourcesPlaceholderConfigurer.java:153)
        at org.springframework.context.support.PropertySourcesPlaceholderConfigurer$1.getProperty(PropertySourcesPlaceholderConfigurer.java:149)
        at org.springframework.core.env.PropertySourcesPropertyResolver.getProperty(PropertySourcesPropertyResolver.java:85)
        at org.springframework.core.env.PropertySourcesPropertyResolver.getPropertyAsRawString(PropertySourcesPropertyResolver.java:74)
        at org.springframework.util.PropertyPlaceholderHelper.parseStringValue(PropertyPlaceholderHelper.java:153)
        at org.springframework.util.PropertyPlaceholderHelper.replacePlaceholders(PropertyPlaceholderHelper.java:126)
        at org.springframework.core.env.AbstractPropertyResolver.doResolvePlaceholders(AbstractPropertyResolver.java:239)
        at org.springframework.core.env.AbstractPropertyResolver.resolveRequiredPlaceholders(AbstractPropertyResolver.java:210)
        at org.springframework.context.support.PropertySourcesPlaceholderConfigurer.lambda$processProperties$0(PropertySourcesPlaceholderConfigurer.java:200)
        at org.springframework.beans.factory.support.AbstractBeanFactory.resolveEmbeddedValue(AbstractBeanFactory.java:964)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1374)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
        at org.springframework.beans.factory.aot.AutowiredFieldValueResolver.resolveValue(AutowiredFieldValueResolver.java:188)
        at org.springframework.beans.factory.aot.AutowiredFieldValueResolver.resolveAndSet(AutowiredFieldValueResolver.java:167)
        at com.example.demo.config.AppConfig__Autowiring.apply(AppConfig__Autowiring.java:16)
        at org.springframework.beans.factory.support.InstanceSupplier$1.get(InstanceSupplier.java:83)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.obtainInstanceFromSupplier(DefaultListableBeanFactory.java:949)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainFromSupplier(AbstractAutowireCapableBeanFactory.java:1237)
        ... 18 more

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ export TEST_VAR1=123

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ ./target/demo.exe

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.3)

2024-09-01T11:05:48.224+01:00  INFO 19736 --- [demo] [           main] com.example.demo.DemoApplication         : Starting AOT-processed DemoApplication using Java 21.0.2 with PID 19736 (C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\demo.exe started by ozkan in C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables)
2024-09-01T11:05:48.224+01:00  INFO 19736 --- [demo] [           main] com.example.demo.DemoApplication         : No active profile set, falling back to 1 default profile: "default"
2024-09-01T11:05:48.247+01:00  INFO 19736 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-09-01T11:05:48.251+01:00  INFO 19736 --- [demo] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-09-01T11:05:48.251+01:00  INFO 19736 --- [demo] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.28]
2024-09-01T11:05:48.254+01:00  INFO 19736 --- [demo] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-09-01T11:05:48.254+01:00  INFO 19736 --- [demo] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 30 ms
2024-09-01T11:05:48.278+01:00  INFO 19736 --- [demo] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-09-01T11:05:48.278+01:00  INFO 19736 --- [demo] [           main] com.example.demo.DemoApplication         : Started DemoApplication in 0.068 seconds (process running for 0.068)
2024-09-01T11:06:06.149+01:00  INFO 19736 --- [demo] [nio-8080-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-09-01T11:06:06.149+01:00  INFO 19736 --- [demo] [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-09-01T11:06:06.149+01:00  INFO 19736 --- [demo] [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
```

