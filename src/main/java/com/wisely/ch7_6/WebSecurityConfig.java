package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//
			.antMatchers("/","login").permitAll()//这个路径不拦截
			.anyRequest().authenticated()//
			.and()//
			.formLogin()//
			.loginPage("/login")//登录页面的路径
			.defaultSuccessUrl("/chat")//登录成功转向的页面
			.permitAll()//
			.and()//
			.logout()//
			.permitAll();
	}
	
	//内存中分配两个用户
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("wyf").password("wyf").roles("USER")
			.and()
			.withUser("wisely").password("wisely").roles("USER");
	}
	
	//静态资源不拦截
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/static/**");
	}

}
