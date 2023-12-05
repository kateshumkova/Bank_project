package com.example.bank_project.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Table(name = "roles")
@Entity
public class RoleEntity implements GrantedAuthority {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    //@Transient
    //@ManyToMany(mappedBy = "roles")
    //private Set<UserEntity> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() { ///имя роли
        return getName();
    }

}
