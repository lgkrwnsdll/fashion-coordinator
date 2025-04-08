package com.example.fashioncoordinator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("Fashion Coordinator API")
            .description("""
                **공통 에러 응답**
                - `400 Bad Request`: 잘못된 요청 데이터 (JSON 파싱 실패 등)
                - `422 Unprocessable Entity`: 요청 데이터의 유효성 검사 실패
                - `500 Internal Server Error`: 서버 내부 처리 중 에러 발생
                """);

        return new OpenAPI().info(info);
    }
}