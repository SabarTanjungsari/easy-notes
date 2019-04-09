package org.sabar.easynotes.configuration;

import javax.sql.DataSource;

import org.sabar.easynotes.exception.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**")
                .permitAll().antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/note/all").permitAll().and().logout()
                .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT usr.name AS username, rl.name AS role "
                        + "FROM ad_user usr JOIN user_role ur ON ur.ad_user_id = usr.ad_user_id "
                        + "JOIN ad_role rl ON rl.ad_role_id = ur.ad_role_id "
                        + "WHERE usr.active = 'Y' AND rl.active = 'Y'AND usr.name=?")
                .usersByUsernameQuery(
                        "select name AS username, password, active as enabled  from ad_user WHERE active = 'Y' AND name=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
