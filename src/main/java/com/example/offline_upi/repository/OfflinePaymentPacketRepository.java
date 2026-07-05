package com.example.offline_upi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offline_upi.entity.OfflinePaymentPacket;
import java.util.Optional;


public interface OfflinePaymentPacketRepository extends JpaRepository<OfflinePaymentPacket,Long>{
    boolean existsByPacketId(String packetId);
    Optional<OfflinePaymentPacket>findByPacketId(String packetId);
}
