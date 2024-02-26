package com.mpicallo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@SpringBootApplication
@ComponentScan(basePackages = "com.mpicallo")
@EntityScan("com.mpicallo")
@EnableSwagger2
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.OAS_30)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"API prices products",
				"Product prices by brand and dates.",
				"1.0",
				"Prices products API terms of service",
				new Contact("Manuel Angel Picallo Perez", "",
						"mapicalloperez@hotmail.com"),
				"Licencia",
				"",
				Collections.emptyList()
		);
	}



}
