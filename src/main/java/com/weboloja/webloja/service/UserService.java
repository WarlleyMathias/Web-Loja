package com.weboloja.webloja.service;

import com.weboloja.webloja.model.CustomOAuth2User;
import com.weboloja.webloja.model.Role;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.model.User.Provider;
import com.weboloja.webloja.repository.RoleRepository;
import com.weboloja.webloja.repository.UserRepository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
 
@Service
public class UserService {
 
    @Autowired
    private UserRepository ur;
    
    @Autowired
    private RoleRepository rr;
     
    public void processOAuthPostLogin(String name, String email) {
        User existUser = ur.findByEmail(email);
        
        Role role = rr.findByName("ROLE_USER");
         
        if (existUser == null) {
        	User user = new User(name, email, Provider.GOOGLE, Arrays.asList(role));
            ur.save(user);        
        }
         
    }
    
    public String nomeUsuarioLogado() {
    	try {
    		CustomOAuth2User oauthUser = new CustomOAuth2User((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	    	return oauthUser.getEmail();
    	}catch(Exception e){
    		return SecurityContextHolder.getContext().getAuthentication().getName();
    	}
    }
    
    public User usuarioLogado() {
    	return ur.findByEmail(nomeUsuarioLogado());
    }
     
}