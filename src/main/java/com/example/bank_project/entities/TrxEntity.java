package com.example.bank_project.entities;

import com.example.bank_project.enums.Status;
import com.example.bank_project.enums.TrxType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity(name="trx")
public class TrxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    @Column (name ="type")
    private TrxType type;

    @Enumerated(EnumType.STRING)
    @Column (name ="status")
    private Status status;

    @PositiveOrZero(message = "Amount of trx must be greater than 0!")
    @Column (name ="amount")
    private BigDecimal amount;

    @Column (name ="description")
    private String description;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

}
