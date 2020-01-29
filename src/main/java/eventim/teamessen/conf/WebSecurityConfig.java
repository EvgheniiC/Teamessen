package eventim.teamessen.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import eventim.teamessen.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoderB() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoderB());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable();
        // The pages does not require login

        http.authorizeRequests()
            .antMatchers("/login**", "/logout**", "/register**", "/registerSuccessful", "/bootstrap/**", "/jquery/**",
                    "/popper/**", "/css/**", "/js/**", "/uploads/**", "/upload/**,/admin/**,/index,/redirect:/,redirect:/")
            .permitAll();
        http.authorizeRequests()
            .antMatchers("/admin/**")
            .access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests()
            .antMatchers("/**")
            .access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests()
            .and()
            .formLogin()//
            // Submit URL of login page.
            .loginProcessingUrl("/j_spring_security_check") // Submit URL
            .loginPage("/login")
            .successHandler(myAuthenticationSuccessHandler())
            //.defaultSuccessUrl("/")//
            .failureUrl("/login?error=true")//
            .usernameParameter("username")//
            .passwordParameter("password")
            // Config for Logout Page
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logoutSuccessful");

        // Config Remember Me
        http.authorizeRequests()
            .and() //
            .rememberMe()
            .tokenRepository(this.persistentTokenRepository()) //
            .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    // Token stored in Memory (Of Web Server).
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
