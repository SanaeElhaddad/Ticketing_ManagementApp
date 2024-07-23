package com.GestionTicket.Service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.GestionTicket.DTO.LoginRequest;
import com.GestionTicket.Entitie.Compte;
import com.GestionTicket.Repository.CompteRepository;


@Service
public class AuthService {
	
	@Autowired
    private CompteRepository compteRepository;

   @Autowired
   JWTUils jwtUils;
   
   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private PasswordEncoder passwordEncoder;

   public LoginRequest register(LoginRequest registrationRequest){
       LoginRequest Req = new LoginRequest();

       try {
           Compte compte = new Compte();
           compte.setEmail(registrationRequest.getEmail());
           compte.setNomUtilisateur(registrationRequest.getNomUtilisateur());
           compte.setRole(registrationRequest.getRole());
           compte.setMotDePasse(passwordEncoder.encode(registrationRequest.getMotDePasse()));
           Compte usersResult = compteRepository.save(compte);
           if (usersResult.getCompteId()>0) {
        	   Req.setCompte(usersResult);
        	   Req.setMessage("User Saved Successfully");
        	   Req.setStatusCode(200);
           }

       }catch (Exception e){
    	   Req.setStatusCode(500);
    	   Req.setErreur(e.getMessage());
       }
       return Req;
   }
   
   public LoginRequest login(LoginRequest loginRequest){
	   LoginRequest response = new LoginRequest();
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                           loginRequest.getMotDePasse()));
           var user = compteRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
           var jwt = jwtUils.generateToken(user);
           var refreshToken = jwtUils.generateRefreshToken(new HashMap<>(), user);
           response.setStatusCode(200);
           response.setToken(jwt);
           response.setRole(user.getRole());
           response.setRefreshToken(refreshToken);
           response.setExpirationTime("24Hrs");
           response.setMessage("Successfully Logged In");

       }catch (Exception e){
           response.setStatusCode(500);
           response.setMessage(e.getMessage());
       }
       return response;
   }
   
   public LoginRequest refreshToken(LoginRequest refreshTokenReqiest){
	   LoginRequest response = new LoginRequest();
       try{
           String ourEmail = jwtUils.extractUsername(refreshTokenReqiest.getToken());
           Compte users = compteRepository.findByEmail(ourEmail).orElseThrow();
           if (jwtUils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
               var jwt = jwtUils.generateToken(users);
               response.setStatusCode(200);
               response.setToken(jwt);
               response.setRefreshToken(refreshTokenReqiest.getToken());
               response.setExpirationTime("24Hr");
               response.setMessage("Successfully Refreshed Token");
           }
           response.setStatusCode(200);
           return response;

       }catch (Exception e){
           response.setStatusCode(500);
           response.setMessage(e.getMessage());
           return response;
       }
   }
   public LoginRequest getAllUsers() {
	   LoginRequest reqRes = new LoginRequest();

       try {
           List<Compte> result = compteRepository.findAll();
           if (!result.isEmpty()) {
               reqRes.setComptes(result);
               reqRes.setStatusCode(200);
               reqRes.setMessage("Successful");
           } else {
               reqRes.setStatusCode(404);
               reqRes.setMessage("No users found");
           }
           return reqRes;
       } catch (Exception e) {
           reqRes.setStatusCode(500);
           reqRes.setMessage("Error occurred: " + e.getMessage());
           return reqRes;
       }
   }

}
