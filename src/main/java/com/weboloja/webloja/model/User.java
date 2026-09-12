package com.weboloja.webloja.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @JsonIgnore
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public enum Provider {
        LOCAL, GOOGLE
    }


    public User(User user) {
        super();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
        this.provider = user.getProvider();
    }
    
    public User(String name, String email, String password, List<Role> roles) {
        super();
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
    
    public User(String name, String email, Provider provider, List<Role> roles) {
        super();
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.provider = provider;
    }

}