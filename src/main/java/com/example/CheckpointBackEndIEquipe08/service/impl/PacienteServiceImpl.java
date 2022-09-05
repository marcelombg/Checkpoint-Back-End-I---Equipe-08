package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
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
        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);
        pacienteRepository.registrar(pacienteEntity);
        return pacienteDTO;
    }

    @Override
    public PacienteDTO buscarID(int id) {
        PacienteEntity pacienteEntity = pacienteRepository.buscarID(id);
        PacienteDTO pacienteDTO = mapperEntityToDTO(pacienteEntity);

        /*String nomePaciente = buscarNome(pacienteEntitie);
        pacienteDTO.setNome(nomePaciente);*/

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return pacienteDTO;
    }

    private String buscarNome(PacienteEntity pacienteEntity) {
        int pacienteId = pacienteEntity.getId();
        PacienteDTO pacienteDTO = buscarID(pacienteId);
        String pacienteNome = pacienteDTO.getNome();
        return pacienteNome;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteEntity> pacienteEntities = pacienteRepository.buscarTodos();
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();

        for (PacienteEntity paciente : pacienteEntities) {
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
        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);

        if (pacienteRepository.buscarID(id) != null){

            pacienteEntity.setId(id);
            pacienteRepository.modificar(pacienteEntity);
            return pacienteDTO;
        }
        else {
            pacienteRepository.registrar(pacienteEntity);
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

    private PacienteEntity mapperDTOToEntity(PacienteDTO pacienteDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteEntity pacienteEntity = objectMapper.convertValue(pacienteDTO, PacienteEntity.class);
        return pacienteEntity;
    }

    private PacienteDTO mapperEntityToDTO(PacienteEntity pacienteEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteDTO pacienteDTO = objectMapper.convertValue(pacienteEntity, PacienteDTO.class);
        return pacienteDTO;
    }
}
