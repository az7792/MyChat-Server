package org.mychat.mychatserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@EnableWebSocket
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyChatServer API")//标题
                        .version("1.0")//描述
                        .description("MyChatServer API"));
    }
}
