package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.service.impl.ConsultaServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.DentistaServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import com.example.CheckpointBackEndIEquipe08.validacoes.ValidationConsulta;
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

    private ValidationConsulta validationConsulta = new ValidationConsulta();

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaDTO> registrar(@RequestBody ConsultaDTO consultaDTO) throws NotFoundException, VariableNullException {
        ResponseEntity responseEntity = null;

        Boolean erro = validationConsulta.validationConsultaVariables(consultaDTO);

        if (!pacienteService.ifPacienteExists(consultaDTO.getPaciente().getId())) {
            responseEntity = new ResponseEntity<>("ID de paciente inválido.", HttpStatus.BAD_REQUEST);
        } else {
            if (!dentistaService.ifDentistaExists(consultaDTO.getDentista().getId())) {
                responseEntity = new ResponseEntity<>("ID de dentista inválido.", HttpStatus.BAD_REQUEST);
            } else {
                if (erro) {
                    ConsultaDTO consultaDTO1 = consultaServiceImpl.registrar(consultaDTO);
                    responseEntity = new ResponseEntity<>(consultaDTO1, HttpStatus.CREATED);
                }
            }
        }

        return responseEntity;
    }

    @GetMapping("/buscar")
    public List<ConsultaDTO> buscarTodos(){
        return consultaServiceImpl.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarID(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(consultaServiceImpl.buscarID(id), HttpStatus.OK);
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

