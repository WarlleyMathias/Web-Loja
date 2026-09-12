package com.weboloja.webloja.config;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.weboloja.webloja.model.Role;
import com.weboloja.webloja.model.User;
import com.weboloja.webloja.repository.RoleRepository;
import com.weboloja.webloja.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent arg0) {

        if (userRepository.findAll().isEmpty()) {
            createUser("Admin", "admin@gmail.com", passwordEncoder.encode("123456"), "ROLE_ADMIN");
            createUser("Cliente", "cliente@gmail.com", passwordEncoder.encode("123456"), "ROLE_USER");
        }

    }

    public void createUser(String name, String email, String password, String roleName) {

        List<Role> role = List.of(new Role(roleName));

        this.roleRepository.save(role.get(0));
        User user = new User(name, email, password, role);
        userRepository.save(user);
    }

}
