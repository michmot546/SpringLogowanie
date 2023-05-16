package com.example.springlogowanie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();


            UserDetails admin = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin1")
                    .roles("ADMIN")
                    .build();

            UserDetails mod = User.withDefaultPasswordEncoder()
                    .username("mod")
                    .password("mod1")
                    .roles("MOD")
                    .build();
        return new InMemoryUserDetailsManager(user,admin,mod);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/").permitAll()
                .antMatchers(HttpMethod.POST,"/api/").hasAnyRole("MOD","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

    }
}
