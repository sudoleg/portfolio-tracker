package com.sudoleg.portfoliomanager;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PortfolioManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioManagerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer apiPrefixConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void configurePathMatch(PathMatchConfigurer configurer) {
				configurer.addPathPrefix("/api/v1", c -> c.isAnnotationPresent(RestController.class));
			}
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		List<String> allowedDomains = List.of(
				"http://localhost:4200",
				"http://localhost:8080",
				"http://localhost");

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(allowedDomains.toArray(new String[0]))
						.allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS");
			}
		};
	}

}
