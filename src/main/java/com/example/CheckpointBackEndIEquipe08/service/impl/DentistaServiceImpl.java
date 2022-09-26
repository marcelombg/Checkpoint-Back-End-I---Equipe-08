package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
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
        DentistaDTO dentistaDTO1 = new DentistaDTO(dentistaEntity);
        return dentistaDTO1;
    }

    @Override
    public DentistaDTO buscarID(int id) throws NotFoundException {
        DentistaEntity dentistaEntity = iDentistaRepository.findById(id).orElseThrow(() -> new NotFoundException("Dentista não encontrado com o id: " + id));
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntity);
        return dentistaDTO;
    }

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
        DentistaEntity dentista = objectMapper.convertValue(dentistaDTO, DentistaEntity.class);
        return dentista;
    }

    private DentistaDTO mapperEntityToDTO(DentistaEntity dentistaEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaDTO dentista = objectMapper.convertValue(dentistaEntity, DentistaDTO.class);
        return dentista;
    }
}