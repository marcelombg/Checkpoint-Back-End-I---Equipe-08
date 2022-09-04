package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
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

//    @GetMapping
//    public Dentista salvar(
//            @RequestParam(value = "id") Integer id,
//            @RequestParam(value = "nome") String nome,
//            @RequestParam(value = "sobrenome") String sobrenome,
//            @RequestParam(value = "matriculaCadastro") Integer matriculaCadastro){
//
//        return dentistaService.registrar(new Dentista(nome, sobrenome, matriculaCadastro));
//    }

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
    public ResponseEntity buscarID(@PathVariable int id) {
        ResponseEntity responseEntity = null;

        DentistaDTO dentistaDTO = dentistaService.buscarID(id);

        if (dentistaDTO != null){
            responseEntity = new ResponseEntity<>(dentistaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    //http://localhost:8082/dentista?id=1&nome=Marcelo&email=marcelo@email.com&numMatrícula=123456789&atendeConvenio=Sim
}
