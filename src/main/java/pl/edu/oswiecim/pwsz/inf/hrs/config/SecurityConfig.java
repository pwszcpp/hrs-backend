package pl.edu.oswiecim.pwsz.inf.hrs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepo userRepository;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().httpBasic().and().headers().and().
                csrf().
                    disable()
                    .authorizeRequests()
                        .antMatchers( "/security/get-session", "/trainings/**","/trainings").hasAuthority("USER")
                        .antMatchers( "/role").hasAuthority("ADMIN")
                        .antMatchers("/","/security/**","/logout","/add/**", "/list","/logout","/employees/**", "/users", "/users/**").permitAll()
                    .and().formLogin()
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                    .loginPage("/login").permitAll()
                    .and().logout().permitAll();
//        http.
//                httpBasic().and()
//                .logout().and()
//                    .authorizeRequests()
//                    .antMatchers("/login").permitAll()
//                .anyRequest().authenticated().and()
//                .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*
   .withUser(userService.findByUsername(username).getUsername())
                .password(userService.findByUsername(username).getPassword())
                .roles(userService.findByUsername(username).getRoles());

                auth.inMemoryAuthentication()
                .withUser("janusz")
                .password("password")
                .roles("USER");
* */