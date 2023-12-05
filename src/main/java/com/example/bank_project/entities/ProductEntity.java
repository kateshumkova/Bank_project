package com.example.bank_project.entities;

import com.example.bank_project.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @NotBlank(message = "Currency could not be blank")
    @Pattern(regexp = "^[A-Z]{3}$", message = "This format is incorrect")
    @Column(name = "currency_code")
    private int currencyCode;

    @PositiveOrZero(message = "Interest rate must be greater than 0")
    @Column(name = "interest_rate")
    private double interestRate;

    @PositiveOrZero(message = "Limit minimum must be greater than 0")
    @Column(name = "limit_min")
    private BigDecimal limitMin;

    @PositiveOrZero(message = "Limit maximum must be greater than 0!")
    @Column(name = "limit_max")
    private BigDecimal limitMax;

    @Column(name = "deposit_period")
    private BigDecimal depositPeriod;

    @Column(name = "payment_frequency")
    private BigDecimal paymentFrequency;

    @CreationTimestamp
    @Column (name ="created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column (name ="updated_at")
    private Date updatedAt;
}

