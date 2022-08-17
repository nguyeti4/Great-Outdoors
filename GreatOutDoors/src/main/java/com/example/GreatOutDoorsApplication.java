package com.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Access Swagger page via: http://localhost:5000/swagger-ui/
// Access Swagger JSON docs via: http://localhost:5000/v2/api-docs

@EnableSwagger2
@Configuration
@SpringBootApplication
public class GreatOutDoorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreatOutDoorsApplication.class, args);
	}
				
	private ApiKey apiKey() {
		return new ApiKey("JWT","Authorization","header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"EmployeeHR API",
				"Sample API for testing purposes",
				"1.0",
				"Free of use",
				new Contact("Timothy Nguyen","https://www.linkedin.com/in/timothy-nguyen-728935173/","timothynguyen2000@yahoo.com"),
				"API License",
				"API license URL",
				Collections.emptyList());
	}	
}
