package com.sqli.topic;

import com.sqli.topic.model.Topic;
import com.sqli.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {

	@Autowired
	TopicRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			repository.save(new Topic( "yassine", "sqli"));
		};
	}
}
