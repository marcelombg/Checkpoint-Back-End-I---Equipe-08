package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PacienteRepository {

    private static Map<Integer, PacienteEntitie> pacienteMap = new HashMap<>();

    private static int idPaciente = 1;

    public PacienteEntitie create(PacienteEntitie pacienteEntitie) {
        pacienteEntitie.setId(idPaciente++);
        pacienteMap.put(pacienteEntitie.getId(), pacienteEntitie);
        return pacienteEntitie;
    }

    public PacienteEntitie getById(int id) {
        return pacienteMap.get(id);
    }

    public String delete(int id){
        pacienteMap.remove(id);
        return "Paciente deletado";
    }

    public PacienteEntitie update(PacienteEntitie pacienteEntitie){
        pacienteMap.put(pacienteEntitie.getId(), pacienteEntitie);
        return pacienteEntitie;
    }

    public List<PacienteEntitie> getAll(){
        List<PacienteEntitie> pacienteEntitie = new ArrayList<>(pacienteMap.values());
        return pacienteEntitie;
//        return new ArrayList<>(pacienteMap.values());
    }
}
