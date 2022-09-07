package com.example.CheckpointBackEndIEquipe08.service.impl;
import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IEnderecoRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoServiceImpl implements IService<EnderecoDTO> {

    @Autowired
    private IEnderecoRepository enderecoRepository;

//    @Autowired
//    private EnderecoServiceImpl enderecoService;

    @Override
    public EnderecoDTO registrar(EnderecoDTO enderecoDTO) {
        EnderecoEntity enderecoEntity = mapperDTOToEntity(enderecoDTO);
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        return enderecoDTO;
    }

    @Override
    public EnderecoDTO buscarID(int id) {
        EnderecoEntity enderecoEntity = enderecoRepository.findById(id).get();
        EnderecoDTO enderecoDTO = mapperEntityToDTO(enderecoEntity);

//        String nomeRua = buscarRua(enderecoEntity);
//        enderecoDTO.setRua(nomeRua);

        return enderecoDTO;
    }

//    private String buscarRua(EnderecoEntity enderecoEntity) {
//        int enderecoId = enderecoEntity.getId();
//        EnderecoDTO enderecoDTO = enderecoService.buscarID(enderecoId);
//        String ruaNome = enderecoDTO.getRua();
//        return ruaNome;
//    }

    @Override
    public List<EnderecoDTO> buscarTodos() {
        List<EnderecoEntity> enderecoEntities = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();

        for (EnderecoEntity endereco : enderecoEntities) {
//            String ruaEndereco = buscarRua(endereco);
            EnderecoDTO enderecoDTO = mapperEntityToDTO(endereco);
//            enderecoDTO.setRua(ruaEndereco);
            enderecoDTOS.add(enderecoDTO);
        }
        return enderecoDTOS;
    }

    @Override
    public String excluir(Integer id) {
        enderecoRepository.deleteById(id);
        return "Endere~p removido";
    }

    @Override
    public EnderecoDTO modificar(EnderecoDTO enderecoDTO, int id) {
        EnderecoEntity enderecoEntity = mapperDTOToEntity(enderecoDTO);
        if (enderecoRepository.findById(id) != null) {


            enderecoEntity.setId(id);
            enderecoRepository.save(enderecoEntity);
            return enderecoDTO;
        } else {
            enderecoRepository.save(enderecoEntity);
            return enderecoDTO;
        }
    }



    private EnderecoEntity mapperDTOToEntity(EnderecoDTO enderecoDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        EnderecoEntity endereco = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);
        return endereco;
    }

    private EnderecoDTO mapperEntityToDTO(EnderecoEntity enderecoEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        EnderecoDTO endereco = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
        return endereco;
    }

    public boolean ifEnderecoExists (int id) {
        return enderecoRepository.existsById(id);
    }
}
