package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl pacienteService;

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDTO> registrar(@RequestBody PacienteDTO pacienteDTO) {
        ResponseEntity responseEntity = null;

        if (pacienteDTO.getNome() != null){
            PacienteDTO pacienteDTO1 = pacienteService.registrar(pacienteDTO);
            responseEntity = new ResponseEntity<>(pacienteDTO1, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Nome não preenchido", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("/buscar")
    public List<PacienteDTO> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return pacienteService.excluir(id);
    }

    @PutMapping("/{id}")
    public PacienteDTO modificar(@RequestBody PacienteDTO pacienteDTO, @PathVariable int id) {
        return pacienteService.modificar(pacienteDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarID(@PathVariable int id) {
        ResponseEntity responseEntity = null;
        PacienteDTO pacienteDTO = pacienteService.buscarID(id);

        if (pacienteDTO != null){
            responseEntity = new ResponseEntity<>(pacienteDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
