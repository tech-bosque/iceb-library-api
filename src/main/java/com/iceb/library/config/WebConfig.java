package com.iceb.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite CORS em todas as rotas
                .allowedOrigins("http://localhost:5173") // Substitua por sua URL local, "*" para permitir todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Cabeçalhos permitidos
                .allowCredentials(true) // Se você envia cookies ou credenciais de autenticação
                .maxAge(3600); // Tempo máximo que a resposta pode ser cacheada pelo navegador
    }
}