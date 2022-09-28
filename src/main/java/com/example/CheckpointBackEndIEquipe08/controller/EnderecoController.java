package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.service.impl.EnderecoServiceImpl;
import com.example.CheckpointBackEndIEquipe08.validacoes.ValidationEndereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoServiceImpl enderecoService;

    private ValidationEndereco validationEndereco = new ValidationEndereco();

    @Operation(summary = "Registrar Endereço do Paciente", description = "Para registrar um endereço informe rua, nº," +
            " cidade, estado, pais e CEP do Paciente.")
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

    @Operation(summary = "Buscar todos endereços salvos")
    @GetMapping("/buscar")
    public List<EnderecoDTO> buscarTodos() {
        return enderecoService.buscarTodos();
    }

    @Operation(summary = "Deletar um endereço pelo ID")
    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Integer id) {
        return enderecoService.excluir(id);
    }

    @Operation(summary = "Alterar um endereço pelo ID")
    @PutMapping("/{id}")
    public EnderecoDTO modificar(@RequestBody EnderecoDTO enderecoDTO, @PathVariable int id) {
        return enderecoService.modificar(enderecoDTO, id);
    }
    @Operation(summary = "Buscar um endereço pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarID(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(enderecoService.buscarID(id), HttpStatus.OK);
    }
}
