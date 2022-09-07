package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import com.example.CheckpointBackEndIEquipe08.repository.PacienteRepository_DESATIVADA;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

//    private static Map<Integer, Paciente> usuarioMap = new HashMap<>();

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    /*@Autowired
    private DentistaServiceImpl dentistaService;*/

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) {
//        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);
//        pacienteEntity = iPacienteRepository.save(pacienteEntity);
//        pacienteDTO = new PacienteDTO(pacienteEntity);
//        return pacienteDTO;

        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);
        EnderecoDTO enderecoDTO;
        int idEndereco = pacienteEntity.getEndereco().getId();

        if(enderecoService.ifEnderecoExists(idEndereco)){
            enderecoDTO = enderecoService.buscarID(idEndereco);
            EnderecoEntity enderecoEntity = new EnderecoEntity(enderecoDTO);
            pacienteEntity.setEndereco(enderecoEntity);
            pacienteEntity = iPacienteRepository.save(pacienteEntity);
        }
        pacienteDTO = mapperEntityToDTO(pacienteEntity);
        return pacienteDTO;
    }

    @Override
    public PacienteDTO buscarID(int id) {
        PacienteEntity pacienteEntity = iPacienteRepository.findById(id).get();
        PacienteDTO pacienteDTO = mapperEntityToDTO(pacienteEntity);

        /*String nomePaciente = buscarNome(pacienteEntitie);
        pacienteDTO.setNome(nomePaciente);*/

        //productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return pacienteDTO;
    }

    private String buscarNome(PacienteEntity pacienteEntity) {
        int pacienteId = pacienteEntity.getId();
        PacienteDTO pacienteDTO = buscarID(pacienteId);
        String pacienteNome = pacienteDTO.getNome();
        return pacienteNome;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteEntity> pacienteEntities = iPacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();

        for (PacienteEntity paciente : pacienteEntities) {
            /*String nomePaciente = buscarNome(paciente);*/
            PacienteDTO pacienteDTO = mapperEntityToDTO(paciente);
            /*pacienteDTO.setNome(nomePaciente);*/
            pacienteDTOS.add(pacienteDTO);
        }
        return pacienteDTOS;
    }

    @Override
    public String excluir(Integer id) {
//        iPacienteRepository.excluir(id);
        iPacienteRepository.deleteById(id);
        return "Paciente removido";
    }

    @Override
    public PacienteDTO modificar(PacienteDTO pacienteDTO, int id) {
        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);

//        if (iPacienteRepository.buscarID(id) != null){
        if (iPacienteRepository.findById(id) != null){


                pacienteEntity.setId(id);
//            iPacienteRepository.modificar(pacienteEntity);
            iPacienteRepository.save(pacienteEntity);
            return pacienteDTO;
        }
        else {
//            iPacienteRepository.registrar(pacienteEntity);
            iPacienteRepository.save(pacienteEntity);
            return pacienteDTO;
        }

        /*String nomePaciente = pacienteDTO.getNome();

        int pacienteId = buscarNome(nomePaciente);*/

        /*pacienteEntitie.setId(id);*/

       /* if (pacienteEntitie.getId() != 0){

            pacienteRepository.registrar(pacienteEntitie);
        return pacienteDTO;
        }
        return pacienteDTO;*/
    }

    /*public int buscarNome(String name){
        return pacienteRepository.buscarNome(name);
    }*/

    private PacienteEntity mapperDTOToEntity(PacienteDTO pacienteDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteEntity pacienteEntity = objectMapper.convertValue(pacienteDTO, PacienteEntity.class);
        return pacienteEntity;
    }

    private PacienteDTO mapperEntityToDTO(PacienteEntity pacienteEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteDTO pacienteDTO = objectMapper.convertValue(pacienteEntity, PacienteDTO.class);
        return pacienteDTO;
    }
}