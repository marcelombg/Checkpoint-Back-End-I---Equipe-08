package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl pacienteService;

    @PostMapping
    public PacienteDTO registrar(@RequestBody PacienteDTO pacienteDTO) {
        return pacienteService.registrar(pacienteDTO);
    }

    @GetMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return pacienteService.excluir(id);
    }

//    @GetMapping
//    public Paciente salvar(
//            @RequestParam(value = "id") Integer id,
//            @RequestParam(value = "nome") String nome,
//            @RequestParam(value = "sobrenome") String sobrenome,
//            @RequestParam(value = "endereçoId") Integer endereçoId,
//            @RequestParam(value = "RG") String RG,
//            @RequestParam(value = "dataAlta")Date dataAlta)
//    {
//
//        return pacienteService.registrar(new Paciente(id, nome, sobrenome, endereçoId, RG, dataAlta));
//    }

    @GetMapping("/buscar")
    public List<PacienteDTO> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @PutMapping("/{id}")
    public PacienteDTO modificar(@RequestBody PacienteDTO pacienteDTO, @PathVariable int id) {
        return pacienteService.modificar(pacienteDTO, id);
    }


    //http://localhost:8082/paciente?id=2&nome=Felipe&email=felipe@email.com&senha=123456789&nivelAcesso=Vip

}
