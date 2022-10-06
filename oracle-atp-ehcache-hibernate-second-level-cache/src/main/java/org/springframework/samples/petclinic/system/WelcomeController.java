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

package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Controller
class WelcomeController {

	@Autowired
	private ApplicationContext context;

	@Autowired
	JCacheCacheManager cacheManager;

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}

	@GetMapping(path = "/getCacheStats", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getCacheStats() {
		String[] bean = context.getBeanDefinitionNames();
		return ResponseEntity.ok(bean);
	}

	@GetMapping(path = "/getCacheStats2", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getCacheStats2() throws Exception {
		ObjectName objectName = new ObjectName("javax.cache:type=CacheStatistics,CacheManager=urn.X-ehcache.jsr107-default-config,Cache=vets");
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		Object objectInstance = mbs.getAttributes(objectName,new String[]{"CacheGets","CacheHits","CacheMisses","CachePuts"});

		return ResponseEntity.ok(objectInstance);
	}

}
