package com.mascix.module2;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.java.Before;

/**
 * Class to use spring application context while running cucumber
 */
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

	private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

	/**
	 * Need this method so the cucumber will recognize this class as glue and load
	 * spring context configuration
	 */
	@Before
	public void setUp() {
		LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
	}
}