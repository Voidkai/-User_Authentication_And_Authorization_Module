package corp.sap.internal.exp.config;

import corp.sap.internal.exp.config.handler.AuthenticationEntryPointImpl;
import corp.sap.internal.exp.config.handler.AuthenticationFailureHandlerImpl;
import corp.sap.internal.exp.config.handler.AuthenticationSuccessHandlerImpl;
import corp.sap.internal.exp.config.handler.LogoutSuccessHandlerImpl;
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
import org.springframework.security.web.csrf.CsrfFilter;

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
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //configure the way of auth
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //HTTP configuration, includes login and logout, exception and session management and soon
        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/","/login").permitAll()
                    .antMatchers("/api/v3/ticket/**").hasAnyAuthority("create_ticket","query_ticket","update_ticket","delete_ticket")
                    .antMatchers("/api/v3/ticket/getAllTicket").hasAuthority("query_all_ticket")
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                .sessionManagement()
                    .maximumSessions(1);

        CsrfTokenResponseHeaderBindingFilter csrfTokenResponseHeaderBindingFilter = new CsrfTokenResponseHeaderBindingFilter();

        httpSecurity.addFilterAfter(csrfTokenResponseHeaderBindingFilter, CsrfFilter.class);


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
