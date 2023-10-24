package vn.com.devmaster.service.managematerial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                        .antMatchers("/api","/api/listproduct").hasAnyRole("USER")
//                        .antMatchers("/**").permitAll()
//                        .anyRequest().authenticated();

//                .formLogin()
//                        .loginPage("/api/login")
//                        .loginProcessingUrl("/api/login-check")
//                    .failureUrl("/api/login?error=true")

//                        .and()
//                .logout().permitAll();
        return http.build();
    }
}
