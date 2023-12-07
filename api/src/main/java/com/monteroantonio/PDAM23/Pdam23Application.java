package com.monteroantonio.PDAM23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Pdam23Application {

	public static void main(String[] args) {

		SpringApplication.run(Pdam23Application.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		String[] allowDomains = new String[2];
		allowDomains[0] = "http://localhost:5173";
		allowDomains[1] = "http://localhost:8080";

		System.out.println("CORS configuration....");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins(allowDomains);
			}
		};
	}

}
