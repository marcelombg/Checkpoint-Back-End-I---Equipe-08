package com.example.CheckpointBackEndIEquipe08.controller;


import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ConsultaController {
    @Autowired
    ConsultaServiceImpl consultaServiceImpl;

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaDTO> cadastrarConsulta(@RequestBody ConsultaEntity consultaEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaServiceImpl.salvarConsulta(consultaEntity));
    }
}

