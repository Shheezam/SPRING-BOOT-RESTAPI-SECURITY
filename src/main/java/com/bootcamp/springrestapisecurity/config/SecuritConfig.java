package com.bootcamp.springrestapisecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class SecuritConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/students/getAllStudents").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/students/getStudentById/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/students/addStudent").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/students/updateById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/students/deleteById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/students/enroll/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/students/drop/{id}").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/courses/getAllCourses").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/courses/getCourseById/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/courses/addCourse").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/courses/updateCourseById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/courses/deleteCourseById/{id}").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}