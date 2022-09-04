package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.PacienteRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

//    private static Map<Integer, Paciente> usuarioMap = new HashMap<>();

    @Autowired
    private PacienteRepository pacienteRepository;

    /*@Autowired
    private DentistaServiceImpl dentistaService;*/

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) {
        PacienteEntitie pacienteEntitie = mapperDTOToEntity(pacienteDTO);
        pacienteRepository.registrar(pacienteEntitie);
        return pacienteDTO;
    }

    @Override
    public PacienteDTO buscarID(int id) {
        PacienteEntitie pacienteEntitie = pacienteRepository.buscarID(id);
        PacienteDTO pacienteDTO = mapperEntityToDTO(pacienteEntitie);

        /*String nomePaciente = buscarNome(pacienteEntitie);
        pacienteDTO.setNome(nomePaciente);*/

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return pacienteDTO;
    }

    private String buscarNome(PacienteEntitie pacienteEntitie) {
        int pacienteId = pacienteEntitie.getId();
        PacienteDTO pacienteDTO = buscarID(pacienteId);
        String pacienteNome = pacienteDTO.getNome();
        return pacienteNome;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteEntitie> pacienteEntities = pacienteRepository.buscarTodos();
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();

        for (PacienteEntitie paciente : pacienteEntities) {
            /*String nomePaciente = buscarNome(paciente);*/
            PacienteDTO pacienteDTO = mapperEntityToDTO(paciente);
            /*pacienteDTO.setNome(nomePaciente);*/
            pacienteDTOS.add(pacienteDTO);
        }
        return pacienteDTOS;
    }

    @Override
    public String excluir(Integer id) {
        pacienteRepository.excluir(id);
        return "Paciente removido";
    }

    @Override
    public PacienteDTO modificar(PacienteDTO pacienteDTO, int id) {
        PacienteEntitie pacienteEntitie = mapperDTOToEntity(pacienteDTO);

        if (pacienteRepository.buscarID(id) != null){

            pacienteEntitie.setId(id);
            pacienteRepository.modificar(pacienteEntitie);
            return pacienteDTO;
        }
        else {
            pacienteRepository.registrar(pacienteEntitie);
            return pacienteDTO;
        }

        /*String nomePaciente = pacienteDTO.getNome();

        int pacienteId = buscarNome(nomePaciente);*/

        /*pacienteEntitie.setId(id);*/

       /* if (pacienteEntitie.getId() != 0){

            pacienteRepository.registrar(pacienteEntitie);
        return pacienteDTO;
        }
        return pacienteDTO;*/
    }

    /*public int buscarNome(String name){
        return pacienteRepository.buscarNome(name);
    }*/

    private PacienteEntitie mapperDTOToEntity(PacienteDTO pacienteDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteEntitie pacienteEntitie = objectMapper.convertValue(pacienteDTO, PacienteEntitie.class);
        return pacienteEntitie;
    }

    private PacienteDTO mapperEntityToDTO(PacienteEntitie pacienteEntitie){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteDTO pacienteDTO = objectMapper.convertValue(pacienteEntitie, PacienteDTO.class);
        return pacienteDTO;
    }
}
