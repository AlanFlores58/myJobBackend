package com.newSoftMex.security;

import com.newSoftMex.filter.MyFilter;
import com.newSoftMex.filter.OptionsHeadersFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by alan.flores on 1/3/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM="MY_TEST_REALM";

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username,user_role.name from user left outer join user_role on user.Id_role = user_role.id where username=?");

        //Register provider
        auth.authenticationProvider(new JWTAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .addFilterBefore(this.getApplicationContext().getBean(MyFilter.class), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/private/**").hasAnyRole("ADMIN","RETAIL","USER","PREMIUM")
                .and().httpBasic()//.realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    //bean for AuthenticationManager
    @Bean
    public AuthenticationManager getAuthenticationManager(){
        try {
            return authenticationManager();
        } catch (Exception e){

        }
        return null;
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
}
