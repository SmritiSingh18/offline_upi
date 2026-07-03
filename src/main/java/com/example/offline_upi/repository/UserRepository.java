package com.example.offline_upi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.offline_upi.entity.user;

@Repository
public interface UserRepository extends JpaRepository<user,Long> {
    Optional<user>  findByPhoneNumber(String phoneNumber);
    Optional<user>  findByUpiId(String upiId);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUpiId(String upiID);

}
