package com.weboloja.webloja.service;

import com.weboloja.webloja.model.CustomOAuth2User;
import com.weboloja.webloja.model.Role;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.model.User.Provider;
import com.weboloja.webloja.repository.RoleRepository;
import com.weboloja.webloja.repository.UserRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
     
    public void processOAuthPostLogin(String name, String email) {
        List <Role> role = List.of(roleRepository.findByName("ROLE_USER"));
        if (!userRepository.existsByEmail(email)) {
        	User user = new User(name, email, Provider.GOOGLE, role);
            userRepository.save(user);
        }
         
    }
    
    public String nomeUsuarioLogado() {
    	try {
    		CustomOAuth2User oauthUser = new CustomOAuth2User((OAuth2User)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	    	return oauthUser.getEmail();
    	}catch(Exception e){
    		return SecurityContextHolder.getContext().getAuthentication().getName();
    	}
    }
    
    public User usuarioLogado() {
    	return userRepository.findByEmail(nomeUsuarioLogado());
    }
     
}