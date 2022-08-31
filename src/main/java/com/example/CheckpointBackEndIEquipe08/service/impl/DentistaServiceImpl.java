package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.repository.DentistaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
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

    @Override
    public DentistaDTO registrar(DentistaDTO dentistaDTO) {
        DentistaEntitie dentistaEntitie = new DentistaEntitie(dentistaDTO);
        dentistaRepository.create(dentistaEntitie);
        return dentistaDTO;
    }

    @Override
    public DentistaDTO buscarID(int id) {
        return new DentistaDTO(dentistaRepository.getById(id));
    }

    @Override
    public List<DentistaDTO> buscarTodos() {
        return null;
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
}
