package com.tamajit.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public class DemoApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	 
	 @Override
	 public void initialize(ConfigurableApplicationContext ac) {
	  ConfigurableEnvironment appEnvironment = ac.getEnvironment();
//	  appEnvironment.addActiveProfile("demo");
	  Resource resource = ac.getResource("classpath:file.yml");
      YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
      PropertySource<?> yamlTestProperties = yamlTestProperties = sourceLoader.load("yamlTestProperties", resource, null);
      applicationContext.getEnvironment().getPropertySources().addFirst(yamlTestProperties);
	 
	 }
	}