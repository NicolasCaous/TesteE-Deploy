package com.edeploy.teste.configurations;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan({"com.edeploy.teste.configurations","com.edeploy.teste.controllers"})
@EnableAutoConfiguration
public class ApplicationInitializer extends SpringBootServletInitializer {

    @Bean
    WebMvcConfigurer javaScriptLocationConfig() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry
                    .addResourceHandler("/js/*.js")
                    .addResourceLocations("classpath:/js/");
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApplicationInitializer.class);

        Properties properties = new Properties();
        properties.setProperty("spring.resources.static-locations","classpath:/html/, classpath:/js/");
        app.setDefaultProperties(properties);
        app.run(args);
    }
}
