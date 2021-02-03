package org.aquercus.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer resourceResolver(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders(
                                "Access-Control-Allow-Origin",
                                "Access-Control-Allow-Methods",
                                "Access-Control-Allow-Headers",
                                "Access-Control-Max-Age",
                                "Access-Control-Request-Header",
                                "Access-Control-Request-Method"
                        );
            }
        };
    }

    @Bean
    public WebSecurityConfigurerAdapter securifier(){
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.anonymous()
                        .and()
                        .authorizeRequests()
                        .antMatchers("/**")
                        .permitAll()
                        .antMatchers(HttpMethod.OPTIONS,"/**")
                        .permitAll()
                        .and()
                        .authorizeRequests()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .csrf().disable();
                           }
        };
    }
}
