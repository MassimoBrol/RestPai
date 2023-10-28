package com.example.Pai.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService user(DataSource dataSource) {

        // create in memory user
        /*
         * UserDetails user1 =
         * User.withUsername("Massimo").password("{noop}test123").roles("TRENTO").build(
         * );
         * UserDetails admin = User.withUsername("Admin").password("{noop}test123")
         * .roles("TRENTO", "CLES", "MEZZOLOMBARDO").build();
         * UserDetails user2 =
         * User.withUsername("Mario").password("{noop}test123").roles("MEZZOLOMBARDO").
         * build();
         * UserDetails user3 =
         * User.withUsername("Monica").password("{noop}test123").roles("CLES").build();
         */

        // get users from DB
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // return new InMemoryUserDetailsManager(user1, user2, user3, admin);
        return users;

    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfiguration) throws Exception {

        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
                (authorize) -> {
                    authorize.requestMatchers("/api/**").permitAll()
                            .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
