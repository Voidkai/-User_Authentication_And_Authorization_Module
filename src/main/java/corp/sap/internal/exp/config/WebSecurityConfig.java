package corp.sap.internal.exp.config;

import corp.sap.internal.exp.authentication.AuthenticationEntryPointImpl;
import corp.sap.internal.exp.authentication.AuthenticationFailureHandlerImpl;
import corp.sap.internal.exp.authentication.AuthenticationSuccessHandlerImpl;
import corp.sap.internal.exp.authentication.LogoutSuccessHandlerImpl;
import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalAuthentication
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Autowired
    LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //configure the way of auth
        auth.userDetailsService(userDetailsService());
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //HTTP configuration, includes login and logout, exception and session management and soon
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/","/login").permitAll()
                    .and()
                .authorizeRequests().antMatchers("/user/getUser").hasAnyAuthority("query_user").anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .successForwardUrl("/login/success")
                    .failureForwardUrl("/login/failure")
                    .permitAll()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                .sessionManagement()
                    .maximumSessions(1);


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
