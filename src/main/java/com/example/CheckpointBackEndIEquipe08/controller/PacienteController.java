package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.Paciente;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl pacienteService;

    @GetMapping
    public Paciente salvar(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "sobrenome") String sobrenome,
            @RequestParam(value = "endereçoId") Integer endereçoId,
            @RequestParam(value = "RG") String RG,
            @RequestParam(value = "dataAlta")Date dataAlta)
    {

        return pacienteService.registrar(new Paciente(id, nome, sobrenome, endereçoId, RG, dataAlta));
    }

    @GetMapping("/buscar")
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @GetMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return pacienteService.excluir(id);
    }

    //http://localhost:8082/paciente?id=2&nome=Felipe&email=felipe@email.com&senha=123456789&nivelAcesso=Vip

}
