package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.MeshResponse;
import com.example.offline_upi.dto.MeshTransferRequest;
import com.example.offline_upi.service.MeshService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/mesh")
public class MeshController {
    private final MeshService meshService;
     public MeshController(MeshService meshService){
        this.meshService=meshService;
     }
     @PostMapping("/relay")
     public MeshResponse relayPacket(@RequestBody MeshTransferRequest request) { 
         return meshService.relayPacket(request);
     }
     
}
