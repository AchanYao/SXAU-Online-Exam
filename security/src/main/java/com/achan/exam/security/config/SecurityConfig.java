package com.achan.exam.security.config;

import com.achan.exam.common.entity.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Achan
 * @date 2020/1/17
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**").permitAll()
//                .antMatchers("/swagger-ui.html").authenticated()
                .and()
                .formLogin().loginProcessingUrl("/api/login").loginPage("/loginPage")
                .usernameParameter("username").passwordParameter("password")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    httpServletResponse.setStatus(200);
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("status", 200);
                    map.put("msg", principal);
                    ObjectMapper objectMapper = new ObjectMapper();
                    writer.write(objectMapper.writeValueAsString(map));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    httpServletResponse.setStatus(401);
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("status", 401);
                    if (e instanceof LockedException) {
                        map.put("msg", "账户被锁定，登录失败");
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg", "用户名或密码错误");
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "账户被禁用，登录失败");
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg", "账户过期，登录失败");
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg", "密码过期，登录失败");
                    } else {
                        map.put("msg", "登录失败");
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    writer.write(objectMapper.writeValueAsString(map));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .logout().logoutUrl("/api/logout").clearAuthentication(true).invalidateHttpSession(true)
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {

                })
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> httpServletResponse.sendRedirect("/"))
                .and().csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/user/register", "/api/user/password/forget");
    }
}
