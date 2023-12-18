package com.example.bank_project.entities;

import com.example.bank_project.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static jakarta.persistence.CascadeType.*;

import java.time.Instant;
import java.util.Date;


@Getter
@Setter
@Entity(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
    //private List<AccountEntity> accounts;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Pattern(regexp = "^(?:\\+\\d{1,3}\\s?)?\\d{1,4}\\s?(\\d{2,3}\\s?)?\\d{3,9}$", message = "Number is not supported!")
    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private UserEntity user;
}
