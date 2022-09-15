package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IDentistaRepository;
import com.example.CheckpointBackEndIEquipe08.repository.IEnderecoRepository;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IConsultaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IService <ConsultaDTO> {

    @Autowired
    IConsultaRepository iConsultaRepository;

    @Autowired
    DentistaServiceImpl dentistaService;
    @Autowired
    PacienteServiceImpl pacienteService;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IDentistaRepository dentistaRepository;

    @Override
    public ConsultaDTO registrar(ConsultaDTO consultaDTO, DentistaEntity dentistaEntity, PacienteEntity pacienteEntity, int id) {
        ConsultaEntity consultaEntity = mapperDTOToEntity(consultaDTO);
        consultaEntity = iConsultaRepository.save(consultaEntity);
        DentistaEntity dentista = dentistaRepository.findById(id).get();
        PacienteEntity paciente = pacienteRepository.findById(id).get();

        if(dentistaService.ifDentistaExists(dentista.getId()) && pacienteService.ifPacienteExists(paciente.getId())){


            consultaEntity.setDentista(dentistaEntity);
            consultaEntity = iConsultaRepository.saveAndFlush(consultaEntity);
            consultaEntity.setPaciente(pacienteEntity);
            consultaEntity = iConsultaRepository.saveAndFlush(consultaEntity);
        }
        consultaDTO = mapperEntityToDTO(consultaEntity);
        return consultaDTO;
    }

    /*@Override
    public ConsultaDTO registrar(ConsultaDTO consultaDTO) {
        ConsultaEntity consultaEntity = mapperDTOToEntity(consultaDTO);
        consultaEntity = iConsultaRepository.save(consultaEntity);
        consultaDTO = new ConsultaDTO(consultaEntity);
        return consultaDTO;
    }*/

    @Override
    public List<ConsultaDTO> buscarTodos() {
        List<ConsultaEntity> consultaEntities = iConsultaRepository.findAll();
        List<ConsultaDTO> consultaDTOS = new ArrayList<>();

        for (ConsultaEntity consultaEntity : consultaEntities){
            ConsultaDTO consultaDTO = mapperEntityToDTO(consultaEntity);
            consultaDTOS.add(consultaDTO);
        }
        return consultaDTOS;
    }

    @Override
    public String excluir(Integer id) {
        iConsultaRepository.deleteById(id);
        return "Consulta removida";
    }

    @Override
    public ConsultaDTO modificar(ConsultaDTO consultaDTO, int id) {
        ConsultaEntity consultaEntity = mapperDTOToEntity(consultaDTO);

        if(iConsultaRepository.findById(id) != null){
            consultaEntity.setId(id);
            return consultaDTO;
        } else {
            iConsultaRepository.save(consultaEntity);
            return consultaDTO;
        }
    }

    @Override
    public ConsultaDTO buscarID(int id) {
        ConsultaEntity consultaEntity = iConsultaRepository.findById(id).get();
        ConsultaDTO consultaDTO = mapperEntityToDTO(consultaEntity);
        return consultaDTO;
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
