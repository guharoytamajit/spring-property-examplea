package com.tamajit.init;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class DemoApplicationContextInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext ac) {
		ConfigurableEnvironment appEnvironment = ac.getEnvironment();
		appEnvironment.setActiveProfiles("demo1", "demo2");
		// appEnvironment.
		MutablePropertySources propertySources = appEnvironment
				.getPropertySources();
		Map myMap = new HashMap();
		myMap.put("xyz", "myValue");
		propertySources.addFirst(new MapPropertySource("MY_MAP", myMap));

	}
}