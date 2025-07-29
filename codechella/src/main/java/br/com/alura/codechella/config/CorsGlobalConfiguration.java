package br.com.alura.codechella.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**") // Aplica as configurações a todos os endpoints
                .allowedOrigins("http://localhost:5500", "http://localhost:8080") // Domínios permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos nas requisições
                .allowCredentials(true) // Permite o envio de cookies e cabeçalhos de autenticação
                .maxAge(3600); // Tempo em segundos que a resposta preflight pode ser armazenada em cache
    }
}
