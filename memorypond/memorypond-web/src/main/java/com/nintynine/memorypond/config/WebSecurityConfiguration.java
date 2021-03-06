package com.nintynine.memorypond.config;

import com.nintynine.memorypond.domain.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/api/**",
                            "/register",
                            "/main",
                            "/login",
                            "/welcome",
                            "/post/**",
                            "/actuator/**"
                            ).permitAll()
                    .antMatchers("/admin").hasRole(Role.ADMIN.toString())
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/main", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .csrf()
                    .disable()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/main")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/templates/**",
                        "/static/**",
                        "/js/**",
                        "/css/**",
                        "/img/**");
    }
}
