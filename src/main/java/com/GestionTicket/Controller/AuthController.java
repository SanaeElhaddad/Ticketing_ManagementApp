package com.GestionTicket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestionTicket.DTO.LoginRequest;
import com.GestionTicket.Service.AuthService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	
	

    @Autowired 
    AuthService authService;
    
    @PostMapping("/auth/register")
    public ResponseEntity<LoginRequest> regeister(@RequestBody LoginRequest req){
    	
        return ResponseEntity.ok(authService.register(req));
    }
    
    @PostMapping("/auth/login")
    public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest req){
    	System.out.println("Received login request: " + req.getEmail());
        return ResponseEntity.ok(authService.login(req));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<LoginRequest> refreshToken(@RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.refreshToken(req));
    }
    
    @GetMapping("/admin/get-all-users")
    public ResponseEntity<LoginRequest> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());

    }

    
}
