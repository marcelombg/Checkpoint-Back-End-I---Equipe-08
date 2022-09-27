package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.service.impl.DentistaServiceImpl;
import com.example.CheckpointBackEndIEquipe08.validacoes.ValidationDentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaServiceImpl dentistaService;

    ValidationDentista validationDentista = new ValidationDentista();

    @PostMapping("/registrar")
    public ResponseEntity<DentistaDTO> registrar(@RequestBody DentistaDTO dentistaDTO) throws VariableNullException {
        ResponseEntity responseEntity = null;

        Boolean erro = validationDentista.validationDentistaVariables(dentistaDTO);

        if (erro) {
            DentistaDTO dentistaDTO1 = dentistaService.registrar(dentistaDTO);
            responseEntity = new ResponseEntity<>(dentistaDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/buscar")
    public List<DentistaDTO> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return dentistaService.excluir(id);
    }

    @PutMapping("/{id}")
    public DentistaDTO modificar(@RequestBody DentistaDTO dentistaDTO, @PathVariable int id) {
        return dentistaService.modificar(dentistaDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarID(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(dentistaService.buscarID(id), HttpStatus.OK);
    }
}
