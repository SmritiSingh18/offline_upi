package com.example.offline_upi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offline_upi.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    
}
