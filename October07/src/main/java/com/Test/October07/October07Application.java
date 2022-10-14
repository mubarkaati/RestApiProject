package com.Test.October07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class October07Application {

	public static void main(String[] args) {
		SpringApplication.run(October07Application.class, args);
	}

}
