package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaServiceImpl consultaServiceImpl;

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaDTO> registrar(@RequestBody ConsultaDTO consultaDTO){
        ResponseEntity responseEntity = null;

        if (consultaDTO.getIdPaciente() != null){
            if(consultaDTO.getIdDentista() != null){
                ConsultaDTO consultaDTO1 = consultaServiceImpl.registrar(consultaDTO);
                responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.CREATED);
            }
            else {
                responseEntity = new ResponseEntity<>("ID do Dentista não encontrado", HttpStatus.NOT_FOUND);
            }
        } else {
            responseEntity = new ResponseEntity<>("ID do Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/buscar")
    public List<ConsultaDTO> buscarTodos(){
        return consultaServiceImpl.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarID(@PathVariable int id){
        ResponseEntity responseEntity =null;
        ConsultaDTO consultaDTO = consultaServiceImpl.buscarID(id);

        if (consultaDTO != null){
            responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ConsultaDTO modificar(@RequestBody ConsultaDTO consultaDTO,@PathVariable int id){
        return consultaServiceImpl.modificar(consultaDTO,id);
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable int id){
        return consultaServiceImpl.excluir(id);
    }
}

