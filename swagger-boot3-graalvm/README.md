# how to build

```bash
mvn -Pnative native:compile
```
# how to run
```bash
./target/demo
```

# compile

```bash
~/projects/spring-examples/swagger-boot3-graalvm$ mvn -Pnative native:compile
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< org.example:demo >--------------------------
[INFO] Building demo 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] >>> native-maven-plugin:0.9.23:compile (default-cli) > package @ demo >>>
[INFO] 
[INFO] --- native-maven-plugin:0.9.23:add-reachability-metadata (add-reachability-metadata) @ demo ---
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.4.8]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.4.8]: Configuration directory is ch.qos.logback/logback-classic/1.4.1
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.11]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.11]: Configuration directory is org.apache.tomcat.embed/tomcat-embed-core/10.0.20
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Latest version not found!
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: missing.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory is org.hibernate.validator/hibernate-validator/7.0.4.Final
[INFO] [graalvm reachability metadata repository for org.jboss.logging:jboss-logging:3.5.3.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.jboss.logging:jboss-logging:3.5.3.Final]: Configuration directory is org.jboss.logging/jboss-logging/3.5.0.Final
[INFO] 
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:compile (default-compile) @ demo ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ demo ---
[INFO] skip non existing resourceDirectory /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:testCompile (default-testCompile) @ demo ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0:test (default-test) @ demo ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.example.ControllerApiTest
20:32:31.638 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [org.example.ControllerApiTest]: ControllerApiTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
20:32:31.770 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration org.example.Main for test class org.example.ControllerApiTest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-18T20:32:32.154+01:00  WARN 1638727 --- [           main] ory$DuplicateJsonObjectContextCustomizer : 

Found multiple occurrences of org.json.JSONObject on the class path:

	jar:file:/home/oz-mint/.m2/repository/org/json/json/20230227/json-20230227.jar!/org/json/JSONObject.class
	jar:file:/home/oz-mint/.m2/repository/com/vaadin/external/google/android-json/0.0.20131108.vaadin1/android-json-0.0.20131108.vaadin1.jar!/org/json/JSONObject.class

You may wish to exclude one of them to ensure predictable runtime behavior

2023-08-18T20:32:32.170+01:00  INFO 1638727 --- [           main] org.example.ControllerApiTest            : Starting ControllerApiTest using Java 20.0.2 with PID 1638727 (started by oz-mint in /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm)
2023-08-18T20:32:32.171+01:00  INFO 1638727 --- [           main] org.example.ControllerApiTest            : No active profile set, falling back to 1 default profile: "default"
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2023-08-18T20:32:35.296+01:00  INFO 1638727 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-08-18T20:32:35.297+01:00  INFO 1638727 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-08-18T20:32:35.300+01:00  INFO 1638727 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2023-08-18T20:32:35.326+01:00  INFO 1638727 --- [           main] org.example.ControllerApiTest            : Started ControllerApiTest in 3.423 seconds (process running for 4.438)
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.106 s - in org.example.ControllerApiTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- spring-boot-maven-plugin:3.1.2:process-aot (process-aot) @ demo ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-18T20:32:36.578+01:00  INFO 1638903 --- [           main] org.example.Main                         : Starting Main using Java 20.0.2 with PID 1638903 (/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/classes started by oz-mint in /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm)
2023-08-18T20:32:36.581+01:00  INFO 1638903 --- [           main] org.example.Main                         : No active profile set, falling back to 1 default profile: "default"
[INFO] 
[INFO] --- maven-jar-plugin:3.3.0:jar (default-jar) @ demo ---
[INFO] Building jar: /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/demo-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:3.1.2:repackage (repackage) @ demo ---
[INFO] Replacing main artifact /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/demo-1.0-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/demo-1.0-SNAPSHOT.jar.original
[INFO] 
[INFO] <<< native-maven-plugin:0.9.23:compile (default-cli) < package @ demo <<<
[INFO] 
[INFO] 
[INFO] --- native-maven-plugin:0.9.23:compile (default-cli) @ demo ---
[INFO] Found GraalVM installation from GRAALVM_HOME variable.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Latest version not found!
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: missing.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.hibernate.validator:hibernate-validator:8.0.1.Final]: Configuration directory is org.hibernate.validator/hibernate-validator/7.0.4.Final
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.11]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.apache.tomcat.embed:tomcat-embed-core:10.1.11]: Configuration directory is org.apache.tomcat.embed/tomcat-embed-core/10.0.20
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.4.8]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for ch.qos.logback:logback-classic:1.4.8]: Configuration directory is ch.qos.logback/logback-classic/1.4.1
[INFO] [graalvm reachability metadata repository for org.jboss.logging:jboss-logging:3.5.3.Final]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.jboss.logging:jboss-logging:3.5.3.Final]: Configuration directory is org.jboss.logging/jboss-logging/3.5.0.Final
[INFO] Executing: /home/oz-mint/.sdkman/candidates/java/current/bin/native-image -cp /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/classes:/home/oz-mint/.m2/repository/org/json/json/20230227/json-20230227.jar:/home/oz-mint/.m2/repository/org/hibernate/validator/hibernate-validator/8.0.1.Final/hibernate-validator-8.0.1.Final.jar:/home/oz-mint/.m2/repository/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar:/home/oz-mint/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.20.0/log4j-to-slf4j-2.20.0.jar:/home/oz-mint/.m2/repository/io/micrometer/micrometer-observation/1.11.2/micrometer-observation-1.11.2.jar:/home/oz-mint/.m2/repository/org/slf4j/jul-to-slf4j/2.0.7/jul-to-slf4j-2.0.7.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.15.2/jackson-datatype-jsr310-2.15.2.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot/3.1.2/spring-boot-3.1.2.jar:/home/oz-mint/.m2/repository/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar:/home/oz-mint/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.11/tomcat-embed-core-10.1.11.jar:/home/oz-mint/.m2/repository/org/springframework/spring-webmvc/6.0.11/spring-webmvc-6.0.11.jar:/home/oz-mint/.m2/repository/ch/qos/logback/logback-classic/1.4.8/logback-classic-1.4.8.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter-validation/3.1.2/spring-boot-starter-validation-3.1.2.jar:/home/oz-mint/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.11/tomcat-embed-el-10.1.11.jar:/home/oz-mint/.m2/repository/org/springframework/spring-expression/6.0.11/spring-expression-6.0.11.jar:/home/oz-mint/.m2/repository/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar:/home/oz-mint/.m2/repository/io/swagger/core/v3/swagger-annotations-jakarta/2.2.7/swagger-annotations-jakarta-2.2.7.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/3.1.2/spring-boot-autoconfigure-3.1.2.jar:/home/oz-mint/.m2/repository/org/springdoc/springdoc-openapi-starter-common/2.0.0/springdoc-openapi-starter-common-2.0.0.jar:/home/oz-mint/.m2/repository/io/github/classgraph/classgraph/4.8.149/classgraph-4.8.149.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/dataformat/jackson-dataformat-yaml/2.15.2/jackson-dataformat-yaml-2.15.2.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter-logging/3.1.2/spring-boot-starter-logging-3.1.2.jar:/home/oz-mint/.m2/repository/org/springdoc/springdoc-openapi-starter-webmvc-api/2.0.0/springdoc-openapi-starter-webmvc-api-2.0.0.jar:/home/oz-mint/.m2/repository/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar:/home/oz-mint/.m2/repository/org/springframework/spring-aop/6.0.11/spring-aop-6.0.11.jar:/home/oz-mint/.m2/repository/org/springframework/spring-web/6.0.11/spring-web-6.0.11.jar:/home/oz-mint/.m2/repository/org/slf4j/slf4j-api/2.0.7/slf4j-api-2.0.7.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.15.2/jackson-module-parameter-names-2.15.2.jar:/home/oz-mint/.m2/repository/org/springframework/spring-jcl/6.0.11/spring-jcl-6.0.11.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.15.2/jackson-datatype-jdk8-2.15.2.jar:/home/oz-mint/.m2/repository/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter-web/3.1.2/spring-boot-starter-web-3.1.2.jar:/home/oz-mint/.m2/repository/jakarta/annotation/jakarta.annotation-api/2.1.1/jakarta.annotation-api-2.1.1.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/home/oz-mint/.m2/repository/io/swagger/core/v3/swagger-core-jakarta/2.2.7/swagger-core-jakarta-2.2.7.jar:/home/oz-mint/.m2/repository/io/micrometer/micrometer-commons/1.11.2/micrometer-commons-1.11.2.jar:/home/oz-mint/.m2/repository/org/projectlombok/lombok/1.18.28/lombok-1.18.28.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter/3.1.2/spring-boot-starter-3.1.2.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/home/oz-mint/.m2/repository/jakarta/activation/jakarta.activation-api/2.1.2/jakarta.activation-api-2.1.2.jar:/home/oz-mint/.m2/repository/org/springframework/spring-context/6.0.11/spring-context-6.0.11.jar:/home/oz-mint/.m2/repository/ch/qos/logback/logback-core/1.4.8/logback-core-1.4.8.jar:/home/oz-mint/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.11/tomcat-embed-websocket-10.1.11.jar:/home/oz-mint/.m2/repository/org/webjars/webjars-locator-core/0.52/webjars-locator-core-0.52.jar:/home/oz-mint/.m2/repository/org/springframework/spring-beans/6.0.11/spring-beans-6.0.11.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter-json/3.1.2/spring-boot-starter-json-3.1.2.jar:/home/oz-mint/.m2/repository/org/springdoc/springdoc-openapi-starter-webmvc-ui/2.0.0/springdoc-openapi-starter-webmvc-ui-2.0.0.jar:/home/oz-mint/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar:/home/oz-mint/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/3.1.2/spring-boot-starter-tomcat-3.1.2.jar:/home/oz-mint/.m2/repository/org/springframework/spring-core/6.0.11/spring-core-6.0.11.jar:/home/oz-mint/.m2/repository/org/apache/logging/log4j/log4j-api/2.20.0/log4j-api-2.20.0.jar:/home/oz-mint/.m2/repository/org/yaml/snakeyaml/1.33/snakeyaml-1.33.jar:/home/oz-mint/.m2/repository/org/webjars/swagger-ui/4.15.5/swagger-ui-4.15.5.jar:/home/oz-mint/.m2/repository/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar:/home/oz-mint/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/4.0.0/jakarta.xml.bind-api-4.0.0.jar:/home/oz-mint/.m2/repository/io/swagger/core/v3/swagger-models-jakarta/2.2.7/swagger-models-jakarta-2.2.7.jar --no-fallback -H:Path=/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target -H:Name=demo -H:ConfigurationFileDirectories=/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/graalvm-reachability-metadata/4acee53846e4b9892b8ec525cd24504c6b45eb57/org.jboss.logging/jboss-logging/3.5.0.Final,/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/graalvm-reachability-metadata/4acee53846e4b9892b8ec525cd24504c6b45eb57/org.apache.tomcat.embed/tomcat-embed-core/10.0.20,/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/graalvm-reachability-metadata/4acee53846e4b9892b8ec525cd24504c6b45eb57/ch.qos.logback/logback-classic/1.4.1,/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/graalvm-reachability-metadata/4acee53846e4b9892b8ec525cd24504c6b45eb57/org.hibernate.validator/hibernate-validator/7.0.4.Final
========================================================================================================================
GraalVM Native Image: Generating 'demo' (executable)...
========================================================================================================================
Warning: Could not resolve class org.jboss.logmanager.LogManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jboss.logmanager.LogManager.
Warning: Could not resolve class com.sun.el.ExpressionFactoryImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: com.sun.el.ExpressionFactoryImpl.
Warning: Could not resolve class groovy.grape.GrabAnnotationTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.grape.GrabAnnotationTransformation.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovyjarjarantlr.CommonToken for reflection configuration. Reason: java.lang.ClassNotFoundException: groovyjarjarantlr.CommonToken.
Warning: Could not resolve class jakarta.persistence.Persistence for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.Persistence.
Warning: Could not resolve class javafx.beans.value.ObservableValue for reflection configuration. Reason: java.lang.ClassNotFoundException: javafx.beans.value.ObservableValue.
Warning: Could not resolve class javax.money.MonetaryAmount for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.money.MonetaryAmount.
Warning: Could not resolve class org.codehaus.groovy.antlr.GroovySourceAST for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.antlr.GroovySourceAST.
Warning: Could not resolve class org.codehaus.groovy.ast.builder.AstBuilderTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.ast.builder.AstBuilderTransformation.
Warning: Could not resolve class org.codehaus.groovy.runtime.ScriptBytecodeAdapter for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.runtime.ScriptBytecodeAdapter.
Warning: Could not resolve class org.glassfish.expressly.ValueExpressionImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.ValueExpressionImpl.
Warning: Could not resolve class org.glassfish.expressly.parser.AstValue for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.parser.AstValue.
Warning: Could not resolve class org.joda.time.ReadableInstant for reflection configuration. Reason: java.lang.ClassNotFoundException: org.joda.time.ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.future.FutureValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent.PastOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not resolve class org.jboss.logmanager.LogManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jboss.logmanager.LogManager.
Warning: Could not resolve class org.jboss.logmanager.LogManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jboss.logmanager.LogManager.
Warning: Could not resolve class com.sun.el.ExpressionFactoryImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: com.sun.el.ExpressionFactoryImpl.
Warning: Could not resolve class groovy.grape.GrabAnnotationTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.grape.GrabAnnotationTransformation.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovyjarjarantlr.CommonToken for reflection configuration. Reason: java.lang.ClassNotFoundException: groovyjarjarantlr.CommonToken.
Warning: Could not resolve class jakarta.persistence.Persistence for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.Persistence.
Warning: Could not resolve class javafx.beans.value.ObservableValue for reflection configuration. Reason: java.lang.ClassNotFoundException: javafx.beans.value.ObservableValue.
Warning: Could not resolve class javax.money.MonetaryAmount for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.money.MonetaryAmount.
Warning: Could not resolve class org.codehaus.groovy.antlr.GroovySourceAST for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.antlr.GroovySourceAST.
Warning: Could not resolve class org.codehaus.groovy.ast.builder.AstBuilderTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.ast.builder.AstBuilderTransformation.
Warning: Could not resolve class org.codehaus.groovy.runtime.ScriptBytecodeAdapter for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.runtime.ScriptBytecodeAdapter.
Warning: Could not resolve class org.glassfish.expressly.ValueExpressionImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.ValueExpressionImpl.
Warning: Could not resolve class org.glassfish.expressly.parser.AstValue for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.parser.AstValue.
Warning: Could not resolve class org.joda.time.ReadableInstant for reflection configuration. Reason: java.lang.ClassNotFoundException: org.joda.time.ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.future.FutureValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent.PastOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not resolve class com.sun.el.ExpressionFactoryImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: com.sun.el.ExpressionFactoryImpl.
Warning: Could not resolve class groovy.grape.GrabAnnotationTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.grape.GrabAnnotationTransformation.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovy.lang.Script for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.Script.
Warning: Could not resolve class groovyjarjarantlr.CommonToken for reflection configuration. Reason: java.lang.ClassNotFoundException: groovyjarjarantlr.CommonToken.
Warning: Could not resolve class jakarta.persistence.Persistence for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.Persistence.
Warning: Could not resolve class javafx.beans.value.ObservableValue for reflection configuration. Reason: java.lang.ClassNotFoundException: javafx.beans.value.ObservableValue.
Warning: Could not resolve class javax.money.MonetaryAmount for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.money.MonetaryAmount.
Warning: Could not resolve class org.codehaus.groovy.antlr.GroovySourceAST for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.antlr.GroovySourceAST.
Warning: Could not resolve class org.codehaus.groovy.ast.builder.AstBuilderTransformation for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.ast.builder.AstBuilderTransformation.
Warning: Could not resolve class org.codehaus.groovy.runtime.ScriptBytecodeAdapter for reflection configuration. Reason: java.lang.ClassNotFoundException: org.codehaus.groovy.runtime.ScriptBytecodeAdapter.
Warning: Could not resolve class org.glassfish.expressly.ValueExpressionImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.ValueExpressionImpl.
Warning: Could not resolve class org.glassfish.expressly.parser.AstValue for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.expressly.parser.AstValue.
Warning: Could not resolve class org.joda.time.ReadableInstant for reflection configuration. Reason: java.lang.ClassNotFoundException: org.joda.time.ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.future.FutureValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
Warning: Could not register method org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent.PastOrPresentValidatorForReadablePartial.<init>() for reflection. Reason: java.lang.NoClassDefFoundError: org/joda/time/ReadableInstant.
[1/8] Initializing...                                                                                    (7.1s @ 0.23GB)
 Java version: 20.0.2+9, vendor version: Oracle GraalVM 20.0.2+9.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3, PGO: ML-inferred
 C compiler: gcc (linux, x86_64, 11.3.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 1 user-specific feature(s)
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
Field org.apache.commons.logging.LogAdapter#log4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#log4jSlf4jProviderPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jApiPresent set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#romePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jaxb2Present set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2Present set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2XmlPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2SmilePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2CborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#gsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jsonbPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.core.NativeDetector#inNativeImage set to true at build time
Field org.springframework.http.converter.json.Jackson2ObjectMapperBuilder#jackson2XmlPresent set to false at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.boot.logging.java.JavaLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.cglib.core.AbstractClassGenerator#inNativeImage set to true at build time
Field org.springframework.core.KotlinDetector#kotlinPresent set to false at build time
Field org.springframework.core.KotlinDetector#kotlinReflectPresent set to false at build time
Field org.springframework.boot.logging.log4j2.Log4J2LoggingSystem$Factory#PRESENT set to false at build time
Field org.springframework.web.client.RestTemplate#romePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jaxb2Present set to true at build time
Field org.springframework.web.client.RestTemplate#jackson2Present set to true at build time
Field org.springframework.web.client.RestTemplate#jackson2XmlPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2SmilePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2CborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#gsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jsonbPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.web.servlet.view.InternalResourceViewResolver#jstlPresent set to false at build time
Field org.springframework.aot.AotDetector#inNativeImage set to true at build time
Field org.springframework.format.support.DefaultFormattingConversionService#jsr354Present set to false at build time
Field org.springframework.boot.autoconfigure.web.format.WebConversionService#JSR_354_PRESENT set to false at build time
Field org.springframework.web.context.support.StandardServletEnvironment#jndiPresent set to true at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystemProperties#JBOSS_LOGGING_PRESENT set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jaxb2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2XmlPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2SmilePresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#gsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jsonbPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationCborPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.web.context.support.WebApplicationContextUtils#jsfPresent set to false at build time
Field org.springframework.context.event.ApplicationListenerMethodAdapter#reactiveStreamsPresent set to false at build time
Field org.springframework.web.context.request.RequestContextHolder#jsfPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#reactorPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#rxjava3Present set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#kotlinCoroutinesPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#mutinyPresent set to false at build time
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
Field org.springframework.web.servlet.mvc.method.annotation.ReactiveTypeHandler#isContextPropagationPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.ResourceChainRegistration#isWebJarsAssetLocatorPresent set to true at build time
Field org.springframework.web.servlet.support.RequestContext#jstlPresent set to false at build time
[2/8] Performing analysis...  [********]                                                                (94.2s @ 1.77GB)
  19,861 (91.40%) of 21,730 types reachable
  31,811 (67.36%) of 47,228 fields reachable
 105,800 (65.18%) of 162,310 methods reachable
   6,287 types,   863 fields, and 8,432 methods registered for reflection
      63 types,    67 fields, and    55 methods registered for JNI access
       4 native libraries: dl, pthread, rt, z
[3/8] Building universe...                                                                              (11.8s @ 1.74GB)
[4/8] Parsing methods...      [******]                                                                  (36.5s @ 1.34GB)
[5/8] Inlining methods...     [****]                                                                     (4.3s @ 1.46GB)
[6/8] Compiling methods...    [****************]                                                       (254.4s @ 2.01GB)
[7/8] Layouting methods...    [****]                                                                    (16.2s @ 2.15GB)
[8/8] Creating image...       [****]                                                                    (11.8s @ 2.27GB)
  59.80MB (54.24%) for code area:    60,465 compilation units
  46.47MB (42.15%) for image heap:  477,767 objects and 235 resources
   3.99MB ( 3.61%) for other data
 110.26MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  18.14MB java.base                                           12.68MB byte[] for code metadata
   5.54MB tomcat-embed-core-10.1.11.jar                       10.50MB byte[] for embedded resources
   5.51MB svm.jar (Native Image)                               4.74MB byte[] for java.lang.String
   4.58MB java.xml                                             3.55MB java.lang.Class
   2.72MB jackson-databind-2.15.2.jar                          3.51MB java.lang.String
   2.28MB spring-core-6.0.11.jar                               3.05MB byte[] for general heap data
   2.06MB hibernate-validator-8.0.1.Final.jar                  1.41MB byte[] for reflection metadata
   1.85MB spring-boot-3.1.2.jar                              930.98kB com.oracle.svm.core.hub.DynamicHubCompanion
   1.14MB classgraph-4.8.149.jar                             572.81kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
1015.44kB spring-web-6.0.11.jar                              480.24kB java.lang.String[]
  14.58MB for 98 more packages                                 4.36MB for 3839 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 G1GC: Use the G1 GC ('--gc=G1') for improved latency and throughput.
 PGO:  Use Profile-Guided Optimizations ('--pgo') for improved throughput.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
 QBM:  Use the quick build mode ('-Ob') to speed up builds during development.
------------------------------------------------------------------------------------------------------------------------
                       53.9s (12.3% of total time) in 427 GCs | Peak RSS: 3.33GB | CPU load: 7.15
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/demo (executable)
========================================================================================================================
Finished generating 'demo' in 7m 18s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  07:34 min
[INFO] Finished at: 2023-08-18T20:40:03+01:00
[INFO] ------------------------------------------------------------------------

```

# runtime

```bash
~/projects/spring-examples/swagger-boot3-graalvm$ ./target/demo 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-18T20:43:31.699+01:00  INFO 1641366 --- [           main] org.example.Main                         : Starting AOT-processed Main using Java 20.0.2 with PID 1641366 (/home/oz-mint/projects/spring-examples/swagger-boot3-graalvm/target/demo started by oz-mint in /home/oz-mint/projects/spring-examples/swagger-boot3-graalvm)
2023-08-18T20:43:31.699+01:00  INFO 1641366 --- [           main] org.example.Main                         : No active profile set, falling back to 1 default profile: "default"
2023-08-18T20:43:31.721+01:00  INFO 1641366 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-08-18T20:43:31.724+01:00  INFO 1641366 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-08-18T20:43:31.724+01:00  INFO 1641366 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.11]
2023-08-18T20:43:31.732+01:00  INFO 1641366 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-08-18T20:43:31.732+01:00  INFO 1641366 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 33 ms
2023-08-18T20:43:31.789+01:00  INFO 1641366 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-08-18T20:43:31.790+01:00  INFO 1641366 --- [           main] org.example.Main                         : Started Main in 0.11 seconds (process running for 0.141)
2023-08-18T20:43:42.545+01:00  INFO 1641366 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-08-18T20:43:42.545+01:00  INFO 1641366 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-08-18T20:43:42.546+01:00  INFO 1641366 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2023-08-18T20:43:43.104+01:00  INFO 1641366 --- [nio-8080-exec-1] o.springdoc.api.AbstractOpenApiResource  : Init duration for springdoc-openapi is: 21 ms

```