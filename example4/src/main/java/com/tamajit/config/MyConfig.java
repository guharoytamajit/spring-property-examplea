package com.tamajit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tamajit.bean.Employee;

@Configuration
@PropertySources(value={
		@PropertySource("classpath:myProp2.properties"),
		// @propertysource path can have ${}, which is resolved from VM args or
		// Env or from other @PropertySource declared before this.It can also
		// have default value like below seperated by :.
		@PropertySource("myProp${num:8}.properties"),
		//myProp3.properties not present but due to ignoreResourceNotFound=true flag no exception occurs
		@PropertySource(value="file:c:/myProp3.properties",ignoreResourceNotFound=true),
		@PropertySource("WEB-INF/myProp4.properties"),
})
@ComponentScan(basePackages="com.tamajit")
public class MyConfig {
	@Value("${emp1.name}")
	String empName;
	@Autowired
	Environment environment;
	
	@Bean
	public InternalResourceViewResolver irvr(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
    /*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Or substituting ${} inside beans.xml files
     * Remove this bean if you are not using @Value annotations for injecting properties.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
         PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
         propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
         propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
    return propertySourcesPlaceholderConfigurer;
    }
    @Bean
    public Employee employee1() {
		Employee employee = new Employee();
		employee.setName(empName);
		employee.setId(environment.getProperty("emp1.id", Integer.class));
		return employee;
	}
    
}
