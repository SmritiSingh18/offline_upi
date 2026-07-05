package com.example.offline_upi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.offline_upi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>  findByPhoneNumber(String phoneNumber);
    Optional<User>  findByUpiId(String upiId);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUpiId(String upiID);

}
