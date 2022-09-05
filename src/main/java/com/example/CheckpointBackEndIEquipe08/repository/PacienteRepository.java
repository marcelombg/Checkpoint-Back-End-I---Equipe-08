package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PacienteRepository {

    private static Map<Integer, PacienteEntity> pacienteMap = new HashMap<>();

    private static int idPaciente = 1;

    public PacienteEntity registrar(PacienteEntity pacienteEntity) {
        pacienteEntity.setId(idPaciente++);
        pacienteMap.put(pacienteEntity.getId(), pacienteEntity);
        return pacienteEntity;
    }

    public PacienteEntity buscarID(int id) {
        return pacienteMap.get(id);
    }

    public String excluir(int id){
        pacienteMap.remove(id);
        return "Paciente deletado";
    }

    public PacienteEntity modificar(PacienteEntity pacienteEntity){
        pacienteMap.put(pacienteEntity.getId(), pacienteEntity);
        return pacienteEntity;
    }

    public List<PacienteEntity> buscarTodos(){
        List<PacienteEntity> pacienteEntity = new ArrayList<>(pacienteMap.values());
        return pacienteEntity;
//        return new ArrayList<>(pacienteMap.values());
    }

    public int buscarNome(String name) {
        for (PacienteEntity pacienteEntity : pacienteMap.values()) {
            String nomeDentista = pacienteEntity.getNome();
            if (nomeDentista.equalsIgnoreCase(name))
                return pacienteEntity.getId();
        }
        return 0;
    }
}
