package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.service.impl.EnderecoServiceImpl;
import com.example.CheckpointBackEndIEquipe08.validacoes.ValidationEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoServiceImpl enderecoService;

    private ValidationEndereco validationEndereco = new ValidationEndereco();

    @PostMapping("/registrar")
    public ResponseEntity<EnderecoDTO> registrar(@RequestBody EnderecoDTO enderecoDTO) throws VariableNullException {
        ResponseEntity responseEntity = null;

        Boolean erro = validationEndereco.validationEnderecoVariables(enderecoDTO);

        if (erro) {
            EnderecoDTO enderecoDTO1 = enderecoService.registrar(enderecoDTO);
            responseEntity = new ResponseEntity<>(enderecoDTO1, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/buscar")
    public List<EnderecoDTO> buscarTodos() {
        return enderecoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Integer id) {
        return enderecoService.excluir(id);
    }

    @PutMapping("/{id}")
    public EnderecoDTO modificar(@RequestBody EnderecoDTO enderecoDTO, @PathVariable int id) {
        return enderecoService.modificar(enderecoDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarID(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(enderecoService.buscarID(id), HttpStatus.OK);
    }



}
