package com.example.offline_upi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offline_upi.entity.Wallet;
import java.util.List;


public interface WalletRepository extends JpaRepository<Wallet,Long> {
   Optional<Wallet> findByWalletNumber(String walletNumber); 
}
