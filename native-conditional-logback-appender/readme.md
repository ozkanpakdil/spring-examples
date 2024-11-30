# how to build
`mvn -ntp package -Pnative native:compile`

# run the app
curl http://localhost:8080/hello

# expectation
Should log to console hello and if there is no env variable defined `lokiHost` it will not send it to grafana. and in the console we see "hello request"

# example compile output
```shell
C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender>mvn -ntp package -Pnative native:compile
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------------< com.example:demo >--------------------------
[INFO] Building demo 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- native:0.10.3:add-reachability-metadata (add-reachability-metadata) @ demo ---
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ demo ---
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 2 source files with javac [debug parameters release 21] to target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug parameters release 21] to target\test-classes
[INFO]
[INFO] --- surefire:3.5.2:test (default-test) @ demo ---
[WARNING]  Parameter 'systemProperties' is deprecated: Use systemPropertyVariables instead.
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.example.demo.DemoApplicationTests
17:53:48.006 [main] INFO  o.s.t.c.s.AnnotationConfigContextLoaderUtils - Could not detect default configuration classes for test class [com.example.demo.DemoApplicationTests]: DemoApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
17:53:48.116 [main] INFO  o.s.b.t.c.SpringBootTestContextBootstrapper - Found @SpringBootConfiguration com.example.demo.DemoApplication for test class com.example.demo.DemoApplicationTests

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.0)

17:53:48.435 [main] INFO  c.example.demo.DemoApplicationTests - Starting DemoApplicationTests using Java 23.0.1 with PID 14196 (started by ozkan in C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender)
17:53:48.435 [main] INFO  c.example.demo.DemoApplicationTests - No active profile set, falling back to 1 default profile: "default"
17:53:49.276 [main] INFO  c.example.demo.DemoApplicationTests - Started DemoApplicationTests in 1.042 seconds (process running for 2.199)
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
WARNING: A Java agent has been loaded dynamically (C:\Users\ozkan\.m2\repository\net\bytebuddy\byte-buddy-agent\1.15.10\byte-buddy-agent-1.15.10.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.341 s -- in com.example.demo.DemoApplicationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- spring-boot:3.4.0:process-aot (process-aot) @ demo ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.0)

17:53:51.240 [main] INFO  com.example.demo.DemoApplication - Starting DemoApplication using Java 23.0.1 with PID 16380 (C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\classes started by ozkan in C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender)
17:53:51.240 [main] INFO  com.example.demo.DemoApplication - No active profile set, falling back to 1 default profile: "default"
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ demo ---
[INFO] Building jar: C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.4.0:repackage (repackage) @ demo ---
[INFO] Replacing main artifact C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] >>> native:0.10.3:compile (default-cli) > package @ demo >>>
[INFO]
[INFO] --- native:0.10.3:add-reachability-metadata (add-reachability-metadata) @ demo ---
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ demo ---
[INFO] Nothing to compile - all classes are up to date.
[WARNING] Overwriting artifact's file from C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar to C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug parameters release 21] to target\test-classes
[INFO]
[INFO] --- surefire:3.5.2:test (default-test) @ demo ---
[WARNING]  Parameter 'systemProperties' is deprecated: Use systemPropertyVariables instead.
[INFO] Skipping execution of surefire because it has already been run for this configuration
[INFO]
[INFO] --- spring-boot:3.4.0:process-aot (process-aot) @ demo ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.0)

17:53:56.038 [main] INFO  com.example.demo.DemoApplication - Starting DemoApplication using Java 23.0.1 with PID 11720 (C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\classes started by ozkan in C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender)
17:53:56.038 [main] INFO  com.example.demo.DemoApplication - No active profile set, falling back to 1 default profile: "default"
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ demo ---
[INFO] Building jar: C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.4.0:repackage (repackage) @ demo ---
[INFO] Replacing main artifact C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] <<< native:0.10.3:compile (default-cli) < package @ demo <<<
[INFO]
[INFO]
[INFO] --- native:0.10.3:compile (default-cli) @ demo ---
[INFO] Found GraalVM installation from JAVA_HOME variable.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.5.12]: Configuration directory is ch.qos.logback\logback-classic\1.4.9
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for com.fasterxml.jackson.core:jackson-databind:2.18.1]: Configuration directory is com.fasterxml.jackson.core\jackson-databind\2.15.2
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.33]: Configuration directory is org.apache.tomcat.embed\tomcat-embed-core\10.0.20
[INFO] Executing: C:\sdkman\candidates\java\current\bin\native-image.cmd @target\tmp\native-image-13282355146257447620.args
Warning: Using a deprecated option --report-unsupported-elements-at-runtime from 'META-INF\native-image\com.example\demo\native-image.properties' in 'file:///C:/Users/ozkan/projects/spring-examples/native-conditional-logback-appender/target/classes/'. The option is deprecated and will be removed in the future. The use of unsupported elements is always reported at run time.
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
[1/8] Initializing...                                                                                    (7.1s @ 0.14GB)
 Java version: 23.0.1+11, vendor version: GraalVM CE 23.0.1+11.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3
 C compiler: cl.exe (microsoft, x64, 19.41.34120)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 2 user-specific feature(s):
 - com.oracle.svm.thirdparty.gson.GsonFeature
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
------------------------------------------------------------------------------------------------------------------------
 2 experimental option(s) unlocked:
 - '-H:ResourceConfigurationResources' (origin(s): 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-core\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.33/tomcat-embed-core-10.1.33.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-el\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.33/tomcat-embed-el-10.1.33.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-websocket\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.33/tomcat-embed-websocket-10.1.33.jar')
 - '-H:ReflectionConfigurationResources' (origin(s): 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-core\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.33/tomcat-embed-core-10.1.33.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-el\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.33/tomcat-embed-el-10.1.33.jar', 'META-INF\native-image\org.apache.tomcat.embed\tomcat-embed-websocket\native-image.properties' in 'file:///C:/Users/ozkan/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.33/tomcat-embed-websocket-10.1.33.jar')
------------------------------------------------------------------------------------------------------------------------
Build resources:
 - 11.92GB of memory (75.6% of 15.77GB system memory, determined at start)
 - 8 thread(s) (100.0% of 8 available processor(s), determined at start)
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
[2/8] Performing analysis...  [******]                                                                  (50.9s @ 1.90GB)
   18,575 reachable types   (89.9% of   20,662 total)
   28,939 reachable fields  (60.6% of   47,764 total)
   90,513 reachable methods (64.5% of  140,338 total)
    6,336 types,    48 fields, and 1,525 methods registered for reflection
       78 types,    62 fields, and    68 methods registered for JNI access
        5 native libraries: crypt32, ncrypt, psapi, version, winhttp
[3/8] Building universe...                                                                               (8.3s @ 2.14GB)
[4/8] Parsing methods...      [***]                                                                      (6.5s @ 1.36GB)
[5/8] Inlining methods...     [****]                                                                     (4.0s @ 1.60GB)
[6/8] Compiling methods...    [*******]                                                                 (53.2s @ 1.95GB)
[7/8] Laying out methods...   [***]                                                                      (9.0s @ 2.63GB)
[8/8] Creating image...       [***]                                                                      (7.8s @ 2.93GB)
  43.45MB (52.50%) for code area:    58,784 compilation units
  38.81MB (46.89%) for image heap:  428,218 objects and 512 resources
 522.63kB ( 0.62%) for other data
  82.78MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  14.87MB java.base                                           11.38MB byte[] for code metadata
   4.47MB tomcat-embed-core-10.1.33.jar                        6.09MB byte[] for java.lang.String
   3.72MB java.xml                                             4.56MB java.lang.Class
   2.04MB jackson-databind-2.18.1.jar                          4.05MB java.lang.String
   1.70MB svm.jar (Native Image)                               1.56MB com.oracle.svm.core.hub.DynamicHubCompanion
   1.59MB spring-core-6.2.0.jar                                1.21MB byte[] for reflection metadata
   1.57MB spring-boot-3.4.0.jar                              882.23kB byte[] for general heap data
   1.47MB janino-3.1.12.jar                                  865.09kB byte[] for embedded resources
   1.38MB java.net.http                                      822.77kB java.lang.String[]
 963.11kB spring-beans-6.2.0.jar                             671.45kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
   9.30MB for 75 more packages                                 6.81MB for 3734 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
------------------------------------------------------------------------------------------------------------------------
                       13.0s (8.6% of total time) in 2079 GCs | Peak RSS: 3.68GB | CPU load: 4.57
------------------------------------------------------------------------------------------------------------------------
Build artifacts:
 C:\Users\ozkan\projects\spring-examples\native-conditional-logback-appender\target\demo.exe (executable)
========================================================================================================================
Finished generating 'demo' in 2m 30s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:46 min
[INFO] Finished at: 2024-11-30T17:56:31Z
[INFO] ------------------------------------------------------------------------

```