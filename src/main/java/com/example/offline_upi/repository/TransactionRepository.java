package com.example.offline_upi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.offline_upi.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySenderWallerNumberOrReceiverWalletNumber(String senderWalletNumber,String receiverWalletNumber);
}
