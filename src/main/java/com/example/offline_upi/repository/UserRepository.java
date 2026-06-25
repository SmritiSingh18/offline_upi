package com.example.offline_upi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offline_upi.entity.user;


public interface UserRepository extends JpaRepository<user,Long> {
    Optional<user>  findByPhonenumber(String phonenumber);
    Optional<user>  findByUpiId(String upiId);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUpiId(String upiID);
}
