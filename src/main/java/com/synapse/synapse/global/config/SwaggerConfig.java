package com.synapse.synapse.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.info(
				new Info()
					.title("SYNAPSE API")
					.version("v1.0")
					.description("음성 인식 키오스크 SYNAPSE API")
			);
	}

}
