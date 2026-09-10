package com.weboloja.webloja.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.weboloja.webloja.model.CustomOAuth2User;
import com.weboloja.webloja.service.CustomOAuth2UserService;
import com.weboloja.webloja.service.MyUserDetailsService;
import com.weboloja.webloja.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private MyUserDetailsService userDetailsService;
	
	 @Autowired
	 private CustomOAuth2UserService oauthUserService;
	 
	 @Autowired
	 private UserService userService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login","oauth/**").permitAll()
                .antMatchers("/addcarrinho", "/addcarrinho2", "/carrinho", "/dropcarrinho", "/perfil", "/addproduto", "/addcategoria")
                .hasAnyRole("USER","ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
                .failureUrl("/loginError")
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(oauthUserService)
                .and().successHandler(new AuthenticationSuccessHandler() {
             
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                            Authentication authentication) throws IOException, ServletException {
             
                        CustomOAuth2User oauthUser = new CustomOAuth2User((OAuth2User) authentication.getPrincipal());
             
                        userService.processOAuthPostLogin(oauthUser.getName(), oauthUser.getEmail());
             
                        response.sendRedirect(getRedirectUrl(request));
                    }
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	
    protected String getRedirectUrl(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(savedRequest != null) {
                return savedRequest.getRedirectUrl();
            }
        }
        
        return request.getContextPath() + "/";
    }
}
