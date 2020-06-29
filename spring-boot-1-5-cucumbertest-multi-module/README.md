# Cucumber test with multiple spring boot application

I wanted to test in spring boot 1.5 to see if multiple spring boot application run at the same time and "test" works interconnectingly.

you can see it in [CucumberTest](module2/src/test/java/com/mascix/module2/CucumberTest.java) which has module1 controller refference and testing it with a mockMvc to call to controller and compares the string result.

in [cucumber config](module2/src/test/java/com/mascix/module2/CucumberSpringContextConfiguration.java) important part

```
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { 
		com.mascix.module2.MultiModuleApplication.class,
		com.mascix.multimodule1.MultiModuleApplication.class }, 
loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ComponentScan
public class CucumberSpringContextConfiguration {
```

ContextConfiguration contains 2 classes, result is in the logs
```
2020-06-29 09:55:02.592  INFO 22688 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/module2],methods=[GET]}" onto public java.lang.String com.mascix.module2.Controller2.home()
2020-06-29 09:55:02.594  INFO 22688 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/module1],methods=[GET]}" onto public java.lang.String com.mascix.multimodule1.Controller1.home()
```

feature file contains a simple scenario just to bring up cucumber context. no data from there used in the test.