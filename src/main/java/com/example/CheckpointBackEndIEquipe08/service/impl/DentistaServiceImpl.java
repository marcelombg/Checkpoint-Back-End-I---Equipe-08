package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.repository.DentistaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistaServiceImpl implements IService<DentistaDTO> {

//    private static Map<Integer, DentistaEntitie> dentistaMap = new HashMap<>();

    @Autowired
    private DentistaRepository dentistaRepository;

    /*@Autowired
    private PacienteServiceImpl pacienteService;*/
    @Override
    public DentistaDTO registrar(DentistaDTO dentistaDTO) {
        DentistaEntity dentistaEntity = mapperDTOToEntity(dentistaDTO);
        dentistaRepository.registrar(dentistaEntity);
        return dentistaDTO;
    }

    @Override
    public DentistaDTO buscarID(int id) {

        DentistaEntity dentistaEntity = dentistaRepository.buscarId(id);
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntity);

        /*String nomeDentista = buscarNome(dentistaEntitie);
        dentistaDTO.setNome(nomeDentista);*/

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return dentistaDTO;


    }

    private String buscarNome(DentistaEntity dentistaEntity) {
        int dentistaId = dentistaEntity.getId();
        DentistaDTO dentistaDTO = buscarID(dentistaId);
        String dentistaNome = dentistaDTO.getNome();
        return dentistaNome;
    }
    @Override
    public List<DentistaDTO> buscarTodos() {
        List<DentistaEntity> dentistaEntities = dentistaRepository.buscarTodos();
        List<DentistaDTO> dentistaDTOS = new ArrayList<>();

        for (DentistaEntity dentistaEntity : dentistaEntities) {
            /*String nomeDentista = buscarNome(dentistaEntitie);*/
            DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntity);
            /*dentistaDTO.setNome(nomeDentista);*/
            dentistaDTOS.add(dentistaDTO);
        }
        return dentistaDTOS;

    }

    @Override
    public String excluir(Integer id) {
        dentistaRepository.excluir(id);
        return "Dentista removido";
    }

    @Override
    public DentistaDTO modificar(DentistaDTO dentistaDTO, int id) {
        DentistaEntity dentistaEntity = mapperDTOToEntity(dentistaDTO);

        if(dentistaRepository.buscarId(id) != null){

            dentistaEntity.setId(id);
            dentistaRepository.modificar(dentistaEntity);
            return dentistaDTO;
        }

        else{
            dentistaRepository.registrar(dentistaEntity);
            return dentistaDTO;
        }
        /*String nomeDentista = dentistaDTO.getNome();

        int dentistaId = buscarNome(nomeDentista);

        dentistaEntitie.setId(id);

        if (dentistaEntitie.getId() != 0){
            dentistaRepository.registrar(dentistaEntitie);
            return dentistaDTO;
        }
        return null;*/
    }

    /*public int buscarNome(String name) {
        return dentistaRepository.buscarNome(name);
    }*/

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
