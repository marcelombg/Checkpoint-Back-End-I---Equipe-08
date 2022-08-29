package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.model.Dentista;
import com.example.CheckpointBackEndIEquipe08.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaServiceImpl dentistaService;

    @GetMapping
    public Dentista salvar(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "numMatrícula") Integer numMatrícula,
            @RequestParam(value = "atendeConvenio") String atendeConvenio){

        return dentistaService.salvar(new Dentista(id, nome, email, numMatrícula, atendeConvenio));
    }

    @GetMapping("/buscar")
    public List<Dentista> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return dentistaService.excluir(id);
    }

    //http://localhost:8082/dentista?id=1&nome=Marcelo&email=marcelo@email.com&numMatrícula=123456789&atendeConvenio=Sim
}
