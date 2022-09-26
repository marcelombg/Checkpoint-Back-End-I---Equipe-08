package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.UserDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO){

        Boolean create = userService.create(userDTO);

        if(create){
            return new ResponseEntity<>("Usário criado com sucesso.", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Usuário não criado.", HttpStatus.CONFLICT);
    }

}
