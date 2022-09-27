package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.service.impl.EnderecoServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import com.example.CheckpointBackEndIEquipe08.validacoes.ValidationPaciente;
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

    private ValidationPaciente validationPaciente = new ValidationPaciente();

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDTO> registrar(@RequestBody PacienteDTO pacienteDTO) throws NotFoundException, VariableNullException {
        ResponseEntity responseEntity = null;

        Boolean erro = validationPaciente.validationPacienteVariables(pacienteDTO);

        if (!enderecoService.ifEnderecoExists(pacienteDTO.getEndereco().getId())) {
            responseEntity = new ResponseEntity<>("ID de endereço inválido.", HttpStatus.BAD_REQUEST);
        }
        else {
            if (erro) {
                PacienteDTO pacienteDTO1 = pacienteService.registrar(pacienteDTO);
                responseEntity = new ResponseEntity<>(pacienteDTO1, HttpStatus.OK);
            }
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
    public ResponseEntity<PacienteDTO> buscarID(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.buscarID(id), HttpStatus.OK);
    }
}
