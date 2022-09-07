package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EnderecoRepository_DESATIVADA {

    private static Map<Integer, EnderecoEntity> enderecoMap = new HashMap<>();

    private static int idEndereco = 1;

    public EnderecoEntity registrar(EnderecoEntity enderecoEntity) {
        enderecoEntity.setId(idEndereco++);
        enderecoMap.put(enderecoEntity.getId(), enderecoEntity);
        return enderecoEntity;
    }

    public EnderecoEntity buscarID(int id) {
        return enderecoMap.get(id);
    }

    public String excluir(int id){
        enderecoMap.remove(id);
        return "Endereço deletado";
    }

    public EnderecoEntity modificar(EnderecoEntity enderecoEntity){
        enderecoMap.put(enderecoEntity.getId(), enderecoEntity);
        return enderecoEntity;
    }

    public List<EnderecoEntity> buscarTodos(){
        List<EnderecoEntity> enderecoEntity = new ArrayList<>(enderecoMap.values());
        return enderecoEntity;
    }
}
