package com.springbootorm.api;

import javax.sql.DataSource;

import com.google.common.collect.ImmutableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("org.baeldung.security")
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    @Autowired
    DataSource dataSource;

    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    public SecurityJavaConfig() {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    /*@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN").and()
                .withUser("user").password(encoder().encode("userPass")).roles("USER");
    }*/


      @Autowired public void configure(AuthenticationManagerBuilder auth) throws
      Exception { auth.jdbcAuthentication().dataSource(dataSource)
      .usersByUsernameQuery("select user_name,password from users where username=?"
      )
      .authoritiesByUsernameQuery("select username, admin_flag from users where username=?"
      ); }


    /*@Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().and().exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint).and().authorizeRequests().antMatchers("/")
                .permitAll().antMatchers("/").permitAll().antMatchers("/").authenticated().antMatchers("/").permitAll()
                .antMatchers("/**").hasRole("ADMIN").and().formLogin().successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler).and().httpBasic().and().logout();
        http.cors();
    }*/


      @Override protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/").access("')").anyRequest().permitAll
      ().and().formLogin().loginPage("/")
      .usernameParameter("user_name").passwordParameter("password").and().logout()
      .logoutSuccessUrl("/login?logout").and().exceptionHandling().accessDeniedPage
      ("/403").and().csrf(); }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}