package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.service.impl.ConsultaServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.DentistaServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
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

    @Autowired
    DentistaServiceImpl dentistaService;

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaDTO> registrar(@RequestBody ConsultaDTO consultaDTO) throws NotFoundException {
        ResponseEntity responseEntity = null;

        if (consultaDTO.getDentista().getId() !=null && consultaDTO.getDentista().getId() !=0){
            if(consultaDTO.getPaciente().getId() !=null && consultaDTO.getPaciente().getId() !=0){
                ConsultaDTO consultaDTO1 = consultaServiceImpl.registrar(consultaDTO);
                responseEntity = new ResponseEntity<>(consultaDTO1, HttpStatus.OK);
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
        return consultaServiceImpl.modificar(consultaDTO, id);
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable int id){
        return consultaServiceImpl.excluir(id);
    }
}

