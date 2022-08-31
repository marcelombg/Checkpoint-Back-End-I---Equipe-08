package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.PacienteRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

//    private static Map<Integer, Paciente> usuarioMap = new HashMap<>();

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaServiceImpl dentistaService;

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) {
        PacienteEntitie pacienteEntitie = new PacienteEntitie(pacienteDTO);
        pacienteRepository.create(pacienteEntitie);
        return pacienteDTO;
    }


    @Override
    public PacienteDTO buscarID(int id) {
        PacienteEntitie pacienteEntitie = pacienteRepository.getById(id);
        PacienteDTO pacienteDTO = new PacienteDTO(pacienteEntitie);

        String nomePaciente = buscarNome(pacienteEntitie);
        pacienteDTO.setNome(nomePaciente);

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return pacienteDTO;
    }

    private String buscarNome(PacienteEntitie pacienteEntitie) {
        int pacienteId = pacienteEntitie.getId();
        DentistaDTO dentistaDTO = dentistaService.buscarID(pacienteId);
        String pacienteNome = dentistaDTO.getNome();
        return pacienteNome;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteEntitie> pacienteEntities = pacienteRepository.getAll();
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();

        for (PacienteEntitie paciente : pacienteEntities) {
            String nomePaciente = buscarNome(paciente);
            PacienteDTO productDTO = new PacienteDTO(paciente);
            productDTO.setNome(nomePaciente);
            pacienteDTOS.add(productDTO);
        }

        return pacienteDTOS;
    }

    @Override
    public String excluir(Integer id) {
        pacienteRepository.delete(id);
        return "Paciente removido";
    }

    @Override
    public PacienteDTO modificar(PacienteDTO pacienteDTO, int id) {
        PacienteEntitie pacienteEntitie = new PacienteEntitie(pacienteDTO);

        String nomePaciente = pacienteDTO.getNome();

        int pacienteId = dentistaService.buscarNome(nomePaciente);

        pacienteEntitie.setId(pacienteId);
        pacienteEntitie.setId(id);

        if (pacienteEntitie.getId() != 0){
            pacienteRepository.create(pacienteEntitie);
        return pacienteDTO;
        }

        return null;
    }
}
