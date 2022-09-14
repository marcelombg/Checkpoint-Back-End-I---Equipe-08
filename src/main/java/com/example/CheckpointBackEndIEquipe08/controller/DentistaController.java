package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.DentistaServiceImpl;
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

    @PostMapping("/registrar")
    public ResponseEntity<DentistaDTO> registrar(@RequestBody DentistaDTO dentistaDTO) {
        ResponseEntity responseEntity = null;

        if (dentistaDTO.getNome() != null){
            DentistaDTO dentistaDTO1 = dentistaService.registrar(dentistaDTO);
            responseEntity = new ResponseEntity<>(dentistaDTO1, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Nome não preenchido", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<DentistaDTO> buscarID(@PathVariable int id) {
        ResponseEntity responseEntity = null;
        DentistaDTO dentistaDTO = dentistaService.buscarID(id);

        if (dentistaDTO != null){
            responseEntity = new ResponseEntity<>(dentistaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
