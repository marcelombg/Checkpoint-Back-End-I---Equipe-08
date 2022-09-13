package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.EnderecoServiceImpl;
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
    @PostMapping("/registrar")
    public ResponseEntity<EnderecoDTO> registrar(@RequestBody EnderecoDTO enderecoDTO) {
        ResponseEntity responseEntity = null;

        if (enderecoDTO.getRua() != null){
            EnderecoDTO enderecoDTO1 = enderecoService.registrar(enderecoDTO);
            responseEntity = new ResponseEntity<>(enderecoDTO1, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Rua não preenchida.", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/buscar")
    public List<EnderecoDTO> buscarTodos(){
        return enderecoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return enderecoService.excluir(id);
    }

    @PutMapping("/{id}")
    public EnderecoDTO modificar(@RequestBody EnderecoDTO enderecoDTO, @PathVariable int id) {
        return enderecoService.modificar(enderecoDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarID(@PathVariable int id) {
        ResponseEntity responseEntity = null;
        EnderecoDTO enderecoDTO = enderecoService.buscarID(id);

        if (enderecoDTO != null){
            responseEntity = new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
