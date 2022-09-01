package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.DentistaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DentistaServiceImpl implements IService<DentistaDTO> {

//    private static Map<Integer, DentistaEntitie> dentistaMap = new HashMap<>();

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteServiceImpl pacienteService;
    @Override
    public DentistaDTO registrar(DentistaDTO dentistaDTO) {
        DentistaEntitie dentistaEntitie = new DentistaEntitie(dentistaDTO);
        dentistaRepository.create(dentistaEntitie);
        return dentistaDTO;
    }

    @Override
    public DentistaDTO buscarID(int id) {

        DentistaEntitie dentistaEntitie = dentistaRepository.getById(id);
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntitie);

        String nomeDentista = buscarNome(dentistaEntitie);
        dentistaDTO.setNome(nomeDentista);

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return dentistaDTO;


    }

    private String buscarNome(DentistaEntitie dentistaEntitie) {
        int dentistaId = dentistaEntitie.getId();
        PacienteDTO pacienteDTO = pacienteService.buscarID(dentistaId);
        String dentistaNome = pacienteDTO.getNome();
        return dentistaNome;
    }
    @Override
    public List<DentistaDTO> buscarTodos() {
        List<DentistaEntitie> dentistaEntities = dentistaRepository.buscarTodos();
        List<DentistaDTO> dentistaDTOS = new ArrayList<>();

        for (DentistaEntitie dentistaEntitie : dentistaEntities) {
            String nomeDentista = buscarNome(dentistaEntitie);
            DentistaDTO dentistaDTO = mapperEntityToDTO(dentistaEntitie);
            dentistaDTO.setNome(nomeDentista);
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
        return null;
    }

    public int buscarNome(String name) {
        return dentistaRepository.buscarNome(name);
    }

    private DentistaEntitie mapperDTOToEntity(DentistaDTO dentistaDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaEntitie dentistaEntitie = objectMapper.convertValue(dentistaDTO, DentistaEntitie.class);
        return dentistaEntitie;
    }

    private DentistaDTO mapperEntityToDTO(DentistaEntitie dentistaEntitie){
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaDTO dentistaDTO = objectMapper.convertValue(dentistaEntitie, DentistaDTO.class);
        return dentistaDTO;
    }
}
