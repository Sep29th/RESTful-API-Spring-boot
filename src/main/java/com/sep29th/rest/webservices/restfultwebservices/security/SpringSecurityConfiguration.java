//package com.sep29th.rest.webservices.restfultwebservices.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@EnableWebSecurity
//public class SpringSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Add authentication for all requests
//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
//        // Add basic authorization
//        http.httpBasic(withDefaults());
//        // Add cors
////        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
//        // Add csrf
////        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//        http.csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        // Configuration for main domain
//        CorsConfiguration mainDomainConfig = new CorsConfiguration();
//        mainDomainConfig.setAllowedOriginPatterns(Collections.singletonList("https://musong.com"));
//        mainDomainConfig.setAllowedMethods(Arrays.asList("GET", "POST"));
//        mainDomainConfig.addAllowedHeader("Content-Type");
//        mainDomainConfig.addAllowedHeader("Authorization");
//
//        // Configuration for subdomain
//        CorsConfiguration subDomainConfig = new CorsConfiguration();
//        subDomainConfig.setAllowedOriginPatterns(Collections.singletonList("https://*.musong.com"));
//        subDomainConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
//        subDomainConfig.addAllowedHeader("Content-Type");
//        subDomainConfig.addAllowedHeader("Authorization");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", mainDomainConfig);
//        source.registerCorsConfiguration("/**", subDomainConfig);
//
//        return source;
//    }
//
//}
