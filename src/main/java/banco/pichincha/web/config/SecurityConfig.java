package banco.pichincha.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/clientes/**").permitAll()
                        .requestMatchers("/api/v1/cuentas/**").permitAll()
                        .anyRequest().authenticated() // Require auth for all other endpoints
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (POST requests)
        return http.build();
    }
}