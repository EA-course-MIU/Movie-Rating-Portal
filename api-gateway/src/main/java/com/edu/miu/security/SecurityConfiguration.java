package com.edu.miu.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    public static final List<String> SWAGGER_PATH = new ArrayList<>() {{
        add("/v3/api-docs");
        add("/swagger-ui/**");
        add("/swagger-ui.html");
        add("/swagger-resources/**");
        add("/webjars/swagger-ui/**");
    }};
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            //ALLOWING REGISTER API FOR DIRECT ACCESS
            .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, null)).permitAll()
            .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "user-service")).permitAll()
            .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "series-service")).permitAll()
            .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "favorite-service")).permitAll()
            .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "movie-service")).permitAll()
//            .pathMatchers("/v3/api-docs", "/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/swagger-ui/**").permitAll()
            .pathMatchers("/users/login/**").permitAll()
            .pathMatchers("/user-service/login/**").permitAll()
            .pathMatchers("/user/api/v1/register").permitAll()
            //ALL OTHER APIS ARE AUTHENTICATED
            .anyExchange().authenticated()
            .and()
            .csrf().disable()
            .oauth2Login()
            .and()
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
            ));
        return http.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    private String[] getPrefixPaths(List<String> paths, String prefix) {
        List<String> result = paths;
        if (StringUtils.isNotBlank(prefix)) {
            result = paths.stream().map(s -> String.format("/%s%s", prefix, s)).toList();
        }
        return result.toArray(new String[0]);

    }
}
