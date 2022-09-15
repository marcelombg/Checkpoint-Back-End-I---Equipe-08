package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IDentistaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DentistaServiceImpl implements IService<DentistaDTO> {

    @Autowired
    private IDentistaRepository iDentistaRepository;

    @Override
    public DentistaDTO registrar(DentistaDTO dentistaDTO) {

        DentistaEntity dentistaEntity = mapperDTOToEntity(dentistaDTO);

        dentistaEntity = iDentistaRepository.save(dentistaEntity);
        dentistaDTO = mapperEntityToDTO(dentistaEntity);

        return dentistaDTO;
    }

    @Override
    public DentistaDTO buscarID(int id) {
        DentistaEntity dentistaEntity = iDentistaRepository.findById(id).get();
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntity);
        return dentistaDTO;
    }

    /*private String buscarNome(DentistaEntity dentistaEntity) {
        int dentistaId = dentistaEntity.getId();
        DentistaDTO dentistaDTO = buscarID(dentistaId);
        String dentistaNome = dentistaDTO.getNome();
        return dentistaNome;
    }*/
    @Override
    public List<DentistaDTO> buscarTodos() {
        List<DentistaEntity> dentistaEntities = iDentistaRepository.findAll();
        List<DentistaDTO> dentistaDTOS = new ArrayList<>();

        for (DentistaEntity dentistaEntity : dentistaEntities) {
            DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntity);
            dentistaDTOS.add(dentistaDTO);
        }
        return dentistaDTOS;
    }

    @Override
    public String excluir(Integer id) {
        iDentistaRepository.deleteById(id);
        return "Dentista removido";
    }

    @Override
    public DentistaDTO modificar(DentistaDTO dentistaDTO, int id) {
        DentistaEntity dentistaEntity = mapperDTOToEntity(dentistaDTO);

        if(iDentistaRepository.findById(id) != null){

            dentistaEntity.setId(id);
            iDentistaRepository.save(dentistaEntity);
            return dentistaDTO;
        } else {
            iDentistaRepository.save(dentistaEntity);
            return dentistaDTO;
        }
    }

    public boolean ifDentistaExists (int id) {
        return iDentistaRepository.existsById(id);
    }

    private DentistaEntity mapperDTOToEntity(DentistaDTO dentistaDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaEntity dentistaEntity = objectMapper.convertValue(dentistaDTO, DentistaEntity.class);
        return dentistaEntity;
    }

    private DentistaDTO mapperEntityToDTO(DentistaEntity dentistaEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaDTO dentistaDTO = objectMapper.convertValue(dentistaEntity, DentistaDTO.class);
        return dentistaDTO;
    }

}