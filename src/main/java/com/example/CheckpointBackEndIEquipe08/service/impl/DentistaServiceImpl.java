package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.Dentista;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DentistaServiceImpl implements IService<Dentista> {

    private static Map<Integer, Dentista> dentistaMap = new HashMap<>();

    @Override
    public Dentista registrar(Dentista dentista) {
        dentistaMap.put(dentista.getId(), dentista);
        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() {
        return new ArrayList<>(dentistaMap.values());
    }

    @Override
    public String excluir(Integer id) {
        dentistaMap.remove(id);
        return "Paciente removido";
    }

    @Override
    public Dentista modificar(Dentista dentista, int id) {
        return null;
    }
}
