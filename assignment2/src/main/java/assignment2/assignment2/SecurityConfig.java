//package assignment2.assignment2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//public class SecurityConfig {
//
//    @Bean
//    public InMemoryUserDetailsManager user(){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user").password("{noop}user").authorities("read").build()
//        );
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http
//                .csrf(csrf->csrf.disable())
//                .authorizeRequests(auth ->auth.anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(withDefaults())
//                .build();
//
//    }
//}
