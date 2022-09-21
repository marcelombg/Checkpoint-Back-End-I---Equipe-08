package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {


    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) throws NotFoundException {

        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);
        EnderecoDTO enderecoDTO;
        int idEndereco = pacienteEntity.getEndereco().getId();

        if(enderecoService.ifEnderecoExists(idEndereco)){
            enderecoDTO = enderecoService.buscarID(idEndereco);
            EnderecoEntity endereco = new EnderecoEntity(enderecoDTO);
            pacienteEntity.setEndereco(endereco);
            pacienteEntity = iPacienteRepository.save(pacienteEntity);
        }
        pacienteDTO = mapperEntityToDTO(pacienteEntity);
        return pacienteDTO;
    }

    @Override
    public PacienteDTO buscarID(int id) {
        PacienteEntity pacienteEntity = iPacienteRepository.findById(id).get();
        PacienteDTO pacienteDTO = mapperEntityToDTO(pacienteEntity);
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
            PacienteDTO pacienteDTO = mapperEntityToDTO(paciente);
            pacienteDTOS.add(pacienteDTO);
        }
        return pacienteDTOS;
    }

    @Override
    public String excluir(Integer id) {
        iPacienteRepository.deleteById(id);
        return "Paciente removido";
    }

    @Override
    public PacienteDTO modificar(PacienteDTO pacienteDTO, int id) {
        PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);

        if (iPacienteRepository.findById(id) != null){
            pacienteEntity.setId(id);
            iPacienteRepository.save(pacienteEntity);
            return pacienteDTO;
        }
        else {
            iPacienteRepository.save(pacienteEntity);
            return pacienteDTO;
        }
    }

    public boolean ifPacienteExists (int id) {
        return iPacienteRepository.existsById(id);
    }

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