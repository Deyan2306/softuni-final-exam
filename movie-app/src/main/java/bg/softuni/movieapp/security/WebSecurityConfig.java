package bg.softuni.movieapp.security;

import bg.softuni.movieapp.model.enums.UserRole;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.services.impl.MovieAppUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final String rememberMeKey;

    public WebSecurityConfig(@Value("${movieapp.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/users/register", "/users/login", "/users/logout").permitAll()
                        .requestMatchers("/users/logout-success", "/users/login-error").permitAll()
                        .requestMatchers("/profile/settings", "/profile/movies", "/profile/tv-series").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())
                        .requestMatchers("/movie/**", "/tv-series/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole(UserRole.ADMIN.name())
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/users/login")
                        .failureForwardUrl("/users/login-error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home")
                )
                .logout((logout) -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/users/logout-success")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe((rememberMe) -> rememberMe
                        .key(rememberMeKey)
                        .rememberMeParameter("rememberme")
                        .rememberMeCookieName("rememberme")
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MovieAppUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
