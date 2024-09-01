# How to run

`./mvnw spring-boot:run`

# how to build native image and start it

`./mvnw -Pnative native:compile`

then

For windows `set TEST_VAR1=123`
For linux `export TEST_VAR1=123`

then

`./target/env-variables.exe`

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

example native build and `run` output from windows git bash
```shell
ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ mvn -Pnative native:compile
MINGW support requires --add-opens java.base/java.lang=ALL-UNNAMED
MINGW support requires --add-opens java.base/java.lang=ALL-UNNAMED
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< io.github.ozkanpakdil:env-variables >-----------------
[INFO] Building demo 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> native:0.10.2:compile (default-cli) > package @ env-variables >>>
[INFO]
[INFO] --- native:0.10.2:add-reachability-metadata (add-reachability-metadata) @ env-variables ---
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ env-variables ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ env-variables ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ env-variables ---
[INFO] skip non existing resourceDirectory C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ env-variables ---
[INFO] No sources to compile
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ env-variables ---
[WARNING]  Parameter 'systemProperties' is deprecated: Use systemPropertyVariables instead.
[INFO] No tests to run.
[INFO]
[INFO] --- spring-boot:3.3.3:process-aot (process-aot) @ env-variables ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.3)

2024-09-01T11:51:24.268+01:00  INFO 22784 --- [           main] io.github.ozkanpakdil.DemoApplication    : Starting DemoApplication using Java 21.0.2 with PID 22784 (C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\classes started by ozkan in C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables)
2024-09-01T11:51:24.273+01:00  INFO 22784 --- [           main] io.github.ozkanpakdil.DemoApplication    : No active profile set, falling back to 1 default profile: "default"
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ env-variables ---
[INFO] Building jar: C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\env-variables-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.3.3:repackage (repackage) @ env-variables ---
[INFO] Replacing main artifact C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\env-variables-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\env-variables-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] <<< native:0.10.2:compile (default-cli) < package @ env-variables <<<
[INFO]
[INFO]
[INFO] --- native:0.10.2:compile (default-cli) @ env-variables ---
[INFO] Found GraalVM installation from GRAALVM_HOME variable.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.7]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.17.2]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.28]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO] Executing: C:\sdkman\candidates\java\21.0.2-graal\bin\native-image.cmd @target\tmp\native-image-7319987649139969616.args
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-core/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-core/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-websocket/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ReflectionConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-el/tomcat-reflection.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-el/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:ResourceConfigurationResources=META-INF/native-image/org.apache.tomcat.embed/tomcat-embed-websocket/tomcat-resource.json' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
========================================================================================================================
GraalVM Native Image: Generating 'env-variables' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------
[1/8] Initializing...                                                                                   (10.2s @ 0.14GB)
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
Found pending operations, continuing analysis.
[2/8] Performing analysis...  [******]                                                                  (74.2s @ 1.90GB)
   16,847 reachable types   (90.1% of   18,695 total)
   26,129 reachable fields  (64.7% of   40,365 total)
   88,449 reachable methods (64.7% of  136,621 total)
    5,438 types,   446 fields, and 6,293 methods registered for reflection
       78 types,    62 fields, and    68 methods registered for JNI access
        5 native libraries: crypt32, ncrypt, psapi, version, winhttp
[3/8] Building universe...                                                                               (9.2s @ 2.03GB)
[4/8] Parsing methods...      [******]                                                                  (32.3s @ 3.05GB)
[5/8] Inlining methods...     [****]                                                                     (9.8s @ 1.65GB)
[6/8] Compiling methods...    [****************]                                                       (280.1s @ 3.64GB)
[7/8] Layouting methods...    [****]                                                                    (13.4s @ 4.73GB)
[8/8] Creating image...       [****]                                                                    (14.8s @ 1.58GB)
  46.69MB (57.03%) for code area:    51,436 compilation units
  34.55MB (42.19%) for image heap:  418,797 objects and 277 resources
 651.55kB ( 0.78%) for other data
  81.88MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  16.17MB java.base                                           12.93MB byte[] for code metadata
   5.19MB tomcat-embed-core-10.1.28.jar                        5.86MB byte[] for java.lang.String
   4.99MB svm.jar (Native Image)                               3.03MB java.lang.String
   4.16MB java.xml                                             2.99MB java.lang.Class
   2.41MB jackson-databind-2.17.2.jar                          1.39MB byte[] for embedded resources
   1.96MB spring-core-6.1.12.jar                               1.11MB byte[] for reflection metadata
   1.82MB spring-boot-3.3.3.jar                              820.49kB byte[] for general heap data
 905.25kB spring-beans-6.1.12.jar                            789.70kB com.oracle.svm.core.hub.DynamicHubCompanion
 791.89kB spring-web-6.1.12.jar                              560.46kB heap alignment
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
                       37.5s (8.4% of total time) in 200 GCs | Peak RSS: 6.31GB | CPU load: 5.75
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\env-variables.exe (executable)
========================================================================================================================
Finished generating 'env-variables' in 7m 25s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  07:37 min
[INFO] Finished at: 2024-09-01T11:58:59+01:00
[INFO] ------------------------------------------------------------------------

ozkan@hp-envy-2021-i7-nvidia MINGW64 ~/projects/spring-examples/graalvm-use-env-variables (master)
$ ./target/env-variables.exe

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.3)

2024-09-01T11:59:39.354+01:00  INFO 12084 --- [           main] io.github.ozkanpakdil.DemoApplication    : Starting AOT-processed DemoApplication using Java 21.0.2 with PID 12084 (C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables\target\env-variables.exe started by ozkan in C:\Users\ozkan\projects\spring-examples\graalvm-use-env-variables)
2024-09-01T11:59:39.354+01:00  INFO 12084 --- [           main] io.github.ozkanpakdil.DemoApplication    : No active profile set, falling back to 1 default profile: "default"
2024-09-01T11:59:39.371+01:00  INFO 12084 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-09-01T11:59:39.372+01:00  INFO 12084 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-09-01T11:59:39.372+01:00  INFO 12084 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.28]
2024-09-01T11:59:39.379+01:00  INFO 12084 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-09-01T11:59:39.379+01:00  INFO 12084 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 25 ms
2024-09-01T11:59:39.399+01:00  INFO 12084 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-09-01T11:59:39.400+01:00  INFO 12084 --- [           main] io.github.ozkanpakdil.DemoApplication    : Started DemoApplication in 0.057 seconds (process running for 0.063)
2024-09-01T12:00:12.735+01:00  INFO 12084 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-09-01T12:00:12.735+01:00  INFO 12084 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-09-01T12:00:12.735+01:00  INFO 12084 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
```

answer : https://stackoverflow.com/questions/77503386/migrating-springboot-3-app-to-graalvm-and-providing-properties-at-runtime