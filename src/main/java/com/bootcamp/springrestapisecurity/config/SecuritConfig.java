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
                .antMatchers(HttpMethod.GET, "/students/getStudentById").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/students/addStudent").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/students/updateById").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/students/deleteById").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/students/{id}/enroll").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/students/{id}/drop").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/courses/getAllCourses").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/courses/getCourseById").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/courses/addCourse").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/courses/updateCourse").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/courses/deleteCourse").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
