package com.example.offline_upi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="mesh_nodes")
@NoArgsConstructor
@AllArgsConstructor
public class MeshNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nodeId;

    private String packetId;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String encryptedPayLoad;

    private LocalDateTime receivedAt;
    
    private boolean hasInternet;

}
