package com.example.bank_project.entities;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "users_roles")
@Entity
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roles_id")
    private String rolesId;

    @Column(name = "users_id")
    private String usersId;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users",
//            joinColumns = @JoinColumn(name = "users_id"),
//            inverseJoinColumns = @JoinColumn(name = "roles_id"))
//    private Set<RoleEntity> roles = new HashSet<>();
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
