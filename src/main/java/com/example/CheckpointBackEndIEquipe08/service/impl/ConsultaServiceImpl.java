package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
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


    @Override
    public ConsultaDTO registrar(ConsultaDTO consultaDTO) {
        ConsultaEntity consultaEntity = mapperDTOToEntity(consultaDTO);
        PacienteDTO pacienteDTO;
        DentistaDTO dentistaDTO;
        int idDentista = consultaEntity.getDentista().getId();
        int idPaciente = consultaEntity.getPaciente().getId();


        if(dentistaService.ifDentistaExists(idDentista) && pacienteService.ifPacienteExists(idPaciente)){
            dentistaDTO = dentistaService.buscarID(idDentista);
            pacienteDTO = pacienteService.buscarID(idPaciente);


            DentistaEntity dentistaEntity = new DentistaEntity(dentistaDTO);
            PacienteEntity pacienteEntity = new PacienteEntity(pacienteDTO);

            consultaEntity.setDentista(dentistaEntity);
            consultaEntity = iConsultaRepository.save(consultaEntity);
            consultaEntity.setPaciente(pacienteEntity);
            consultaEntity = iConsultaRepository.save(consultaEntity);
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
