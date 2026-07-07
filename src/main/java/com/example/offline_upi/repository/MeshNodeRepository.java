package com.example.offline_upi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offline_upi.entity.MeshNode;
import java.util.List;


public interface MeshNodeRepository extends JpaRepository<MeshNode,Long>{

    List<MeshNode> findByPacketId(String packetId);
    List<MeshNode> findByHasInternet(boolean hasInternet);
    
}
