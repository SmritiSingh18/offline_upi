package com.example.offline_upi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String fullname;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(unique = true,nullable = false)
    private String upiId;

    @Column(nullable = false)
    private String pin;
    
    @OneToOne(
        mappedBy = "User",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private Wallet wallet;

    
}
