//package assignment2.assignment2;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//
//@Configuration
//public class SpringSecurity extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//    http.authorizeRequests().antMatchers("/amount").authenticated()
//            .antMatchers("/all").permitAll().and()
//            .formLogin().and().httpBasic();
//    }
//}
