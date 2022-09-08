package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.repository.IDentistaRepository;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IConsultaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class ConsultaServiceImpl implements IService <ConsultaDTO> {

    @Autowired
    IConsultaRepository iConsultaRepository;

    @Autowired
    IDentistaRepository iDentistaRepository;

    @Autowired
     IPacienteRepository iPacienteRepository;


    @Override
    public ConsultaDTO registrar(ConsultaDTO consultaDTO) {

        ConsultaEntity consultaEntity = mapperDTOToEntity(consultaDTO);
        consultaEntity = iConsultaRepository.save(consultaEntity);

        return consultaDTO;
    }

    @Override
    public List<ConsultaDTO> buscarTodos() {
        List <ConsultaEntity> consultaEntities = iConsultaRepository.findAll();
        List <ConsultaDTO> consultaDTOS = new ArrayList<>();

        for (ConsultaEntity consultaEntity : consultaEntities) {
            ConsultaDTO consultaDTO = mapper
    }


    @Override
    public String excluir(Integer id) {
        return null;
    }

    @Override
    public ConsultaDTO modificar(ConsultaDTO consultaDTO, int id) {
        return null;
    }

    @Override
    public ConsultaDTO buscarID(int id) {
        return null;
    }


    private ConsultaEntity mapperDTOToEntity(ConsultaDTO consultaDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        ConsultaEntity consultaEntity = objectMapper.convertValue(consultaDTO, ConsultaEntity.class);
        return consultaEntity;
    }

    private ConsultaDTO mapperEntityToDTO(ConsultaEntity consultaEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        ConsultaDTO consultaDTO = objectMapper.convertValue(consultaEntity, ConsultaDTO.class);
        return consultaDTO;
    }

}
