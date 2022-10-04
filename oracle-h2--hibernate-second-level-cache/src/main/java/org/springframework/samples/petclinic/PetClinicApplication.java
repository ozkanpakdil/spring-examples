/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testcontainers.containers.OracleContainer;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 */
@SpringBootApplication
@Slf4j
public class PetClinicApplication {

	public static void main(String[] args) {
		OracleContainer oracle = new OracleContainer("gvenzl/oracle-xe:slim-faststart")
			.withDatabaseName("testDB")
			.withUsername("testUser")
			.withPassword("testPassword");

		oracle.start();

		System.out.println("oracle db:" + oracle.getJdbcUrl());
		System.out.println("SID:" + oracle.getSid());
		System.out.println("network:" + oracle.getNetwork());
		System.out.println("exposed ports:" + oracle.getExposedPorts());
		System.out.println("extra hosts:" + oracle.getExtraHosts());


		System.setProperty("spring.datasource.url", oracle.getJdbcUrl());
		System.setProperty("spring.datasource.password", oracle.getPassword());
		System.setProperty("spring.datasource.username", oracle.getUsername());

		SpringApplication.run(PetClinicApplication.class, args);
	}

}
