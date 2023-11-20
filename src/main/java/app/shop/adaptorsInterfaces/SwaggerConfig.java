package app.shop.adaptorsInterfaces;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .displayName("Back-End - Serviço de Vendas")
                .group("spring")
                .pathsToMatch("/**")
                .build();
    }
}
