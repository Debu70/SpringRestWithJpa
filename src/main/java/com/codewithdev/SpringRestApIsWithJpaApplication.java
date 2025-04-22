package com.codewithdev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
class ServerLogger implements CommandLineRunner {

    @Value("${server.port}")
    private String serverPort;

    @Value("${app.name}")
    private String projAuther;

    @Override
    public void run(String... args) {
        System.out.println("✅ Application is running on port: " + serverPort+" By Author : "+ projAuther);
    }
}


@SpringBootApplication
public class SpringRestApIsWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApIsWithJpaApplication.class, args);


	}

}
