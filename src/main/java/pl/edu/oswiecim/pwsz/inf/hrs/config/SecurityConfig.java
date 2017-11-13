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
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepo userRepository;


    @Autowired
    private UserDetailsService customUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/security/get-session").hasAuthority("USER")
                    .antMatchers( "/role").hasAuthority("ADMIN")
                    .antMatchers("/","/security/**","/logout","/add/**", "/list","/logout","/trainings/**", "/trainings","/employees/**").permitAll()
                    .anyRequest().authenticated();
                    /*
                    .and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome").failureUrl("/login?error=true").permitAll()
                    .and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login");
                    */
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