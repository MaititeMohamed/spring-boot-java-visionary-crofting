package com.youcode.visionarycrofting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
public class VisionarycroftingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisionarycroftingApplication.class, args);
	}
}
