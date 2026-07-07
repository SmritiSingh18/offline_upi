package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.MeshRequest;
import com.example.offline_upi.dto.MeshResponse;
import com.example.offline_upi.service.MeshService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/mesh")
public class MeshController {
    private final MeshService meshService;

    MeshController(MeshService meshService){
        this.meshService=meshService;
    }
    @PostMapping("/relay")
    public MeshResponse relayPacket(@RequestBody MeshRequest request) {
        
        return meshService.relayPacket(request);
    }
    
}
