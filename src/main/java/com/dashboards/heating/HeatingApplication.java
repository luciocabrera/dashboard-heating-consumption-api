package com.dashboards.heating;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories // activate reactive mongo support
@SpringBootApplication
public class HeatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatingApplication.class, args);
	}

}
