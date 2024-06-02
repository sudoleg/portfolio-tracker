package com.sudoleg.portfoliomanager;

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

		String[] allowedDomains = new String[2];
		allowedDomains[0] = "http://localhost:4200";
		allowedDomains[1] = "http://localhost:8080";

		System.out.println("CORS configuration....");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(allowedDomains)
						.allowedMethods("*");
			}
		};
	}

}
