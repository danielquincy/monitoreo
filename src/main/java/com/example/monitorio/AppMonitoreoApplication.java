package com.example.monitorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

 @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
 @ComponentScan("com")
public class AppMonitoreoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppMonitoreoApplication.class, args);
	}

}
