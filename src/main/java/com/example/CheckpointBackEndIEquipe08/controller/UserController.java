package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.UserDTO;
import com.example.CheckpointBackEndIEquipe08.security.AuthenticationResponse;
import com.example.CheckpointBackEndIEquipe08.security.JwtUtil;
import com.example.CheckpointBackEndIEquipe08.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(name = "/")
    public String msg(){
        return "<h1> Olá </h1>";
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO){

        Boolean create = userService.create(userDTO);

        if(create){
            return new ResponseEntity<>("Usário criado com sucesso.", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Usuário não criado.", HttpStatus.CONFLICT);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



}
