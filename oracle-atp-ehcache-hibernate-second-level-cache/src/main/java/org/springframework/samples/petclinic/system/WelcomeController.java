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

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Controller
class WelcomeController {

	@Autowired
	private ApplicationContext context;

	@Autowired
	CacheManager cacheManager;

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
		Object objectInstance = mbs.getAttributes(objectName, new String[]{"CacheGets", "CacheHits", "CacheMisses", "CachePuts"});

		return ResponseEntity.ok(objectInstance);
	}

	@GetMapping(path = "/getCacheStatsBigger", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getCacheStatsBigger() throws Exception {
		return ResponseEntity.ok(getStatistics(cacheManager.getCache("vets")));
	}

	public String getStatistics(Cache<? extends Object, ? extends Object> cache) {
		try {
			StringBuffer b = new StringBuffer();
			ObjectName objectName = getJMXObjectName(cache);
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

			// printing retrieved cache statistics to console.
			for (CacheStatistics cacheStatistic : CacheStatistics.values()) {
				b.append(cacheStatistic + "=" + mBeanServer.getAttribute(objectName, cacheStatistic.name()) + "\n");
			}
			return b.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ObjectName getJMXObjectName(Cache<? extends Object, ? extends Object> cache) {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		// Refer to org.ehcache.jsr107.Eh107CacheStatisticsMXBean.Eh107CacheStatisticsMXBean(String, URI, StatisticsService)
		// and org.ehcache.jsr107.Eh107MXBean.Eh107MXBean(String, URI, String)
		final String beanName = "CacheStatistics";
		String cacheManagerName = sanitize(cache.getCacheManager().getURI().toString());
		String cacheName = sanitize(cache.getName());
		ObjectName objectName = null;
		try {
			objectName = new ObjectName(
				"javax.cache:type=" + beanName + ",CacheManager=" + cacheManagerName + ",Cache=" + cacheName);
		} catch (MalformedObjectNameException e) {
			throw new CacheException(e);
		}

		if (!mBeanServer.isRegistered(objectName)) {
			throw new CacheException("No MBean found with ObjectName => " + objectName.getCanonicalName());
		}

		return objectName;
	}

	private String sanitize(String string) {
		return ((string == null) ? "" : string.replaceAll(",|:|=|\n", "."));
	}

	/**
	 * Defining cache statistics parameters as constants.
	 */
	private enum CacheStatistics {
		CacheHits, CacheHitPercentage,
		CacheMisses, CacheMissPercentage,
		CacheGets, CachePuts, CacheRemovals, CacheEvictions,
		AverageGetTime, AveragePutTime, AverageRemoveTime
	}
}
