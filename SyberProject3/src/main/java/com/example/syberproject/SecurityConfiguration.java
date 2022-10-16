//package com.example.syberproject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("user");
//    }
//
//@Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//}
//
//@Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//}
//
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.authorizeRequests().antMatchers("/all").authenticated().and().formLogin().and().httpBasic();
//
//
//
////        @Bean
////        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////            http
////                    .authorizeHttpRequests((requests) -> requests
////
////                            .anyRequest().authenticated()
////                    );
////
////            return http.build();
////        }
//
//
//
////
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("*"));
////        configuration.setAllowedMethods(Arrays.asList("*"));
////        configuration.setAllowedHeaders(Arrays.asList("*"));
////        configuration.setAllowCredentials(true);
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//
//}
//
//
//
//
//
