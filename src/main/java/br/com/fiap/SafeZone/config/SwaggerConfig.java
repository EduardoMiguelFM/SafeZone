package br.com.fiap.SafeZone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SafeZone API")
                        .version("1.0")
                        .description("Documentação interativa da API do projeto SafeZone - Sistema Inteligente de Alerta e Apoio em Desastres Naturais."));
    }
}