package com.sooruth.zuniversity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/","/myAccount/**","/myBalance/**", "/myCard/**", "/myLoan/**", "/students/**").authenticated() // this will only authenticate the mentioned URLs of the application
                .requestMatchers("/users/**").hasAnyAuthority("admin") // only admin and manager should see students details
                .requestMatchers("/notice","/contact", "/client/posts/**").permitAll() //enable this to allow access to these URLs without logging. Useful for testing new API...
                //.anyRequest().authenticated()  //this will secure all URLs of the application
                //.anyRequest().denyAll() //this will deny all requests coming to the application after login because it is authorization here. You will get login page to authenticate.
                .anyRequest().permitAll() //enable this to allow access to all other URLs without logging such as swagger, actuator and graphiql
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        //http.csrf().disable();  //need to disable csrf to allow POST to users
        http.csrf(csrf -> csrf.disable());  //need to disable csrf to allow POST to users
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
