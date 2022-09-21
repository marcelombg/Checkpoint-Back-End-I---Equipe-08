package com.example.CheckpointBackEndIEquipe08.service.impl;
import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;
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

    @Override
    public EnderecoDTO registrar(EnderecoDTO enderecoDTO) {
        EnderecoEntity enderecoEntity = mapperDTOToEntity(enderecoDTO);
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        EnderecoDTO enderecoDTO1 = new EnderecoDTO(enderecoEntity);
        return enderecoDTO1;
    }

    @Override
    public EnderecoDTO buscarID(int id) throws NotFoundException {
        EnderecoEntity enderecoEntity = enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado com o id: " + id));
        EnderecoDTO enderecoDTO = mapperEntityToDTO(enderecoEntity);
        return enderecoDTO;
    }

    @Override
    public List<EnderecoDTO> buscarTodos() {
        List<EnderecoEntity> enderecoEntities = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();

        for (EnderecoEntity endereco : enderecoEntities) {
            EnderecoDTO enderecoDTO = mapperEntityToDTO(endereco);
            enderecoDTOS.add(enderecoDTO);
        }
        return enderecoDTOS;
    }

    @Override
    public String excluir(Integer id) {
        enderecoRepository.deleteById(id);
        return "Endereço removido";
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

    public boolean ifEnderecoExists (int id) {
        return enderecoRepository.existsById(id);
    }

    private EnderecoDTO mapperEntityToDTO(EnderecoEntity enderecoEntity){
        ObjectMapper objectMapper = new ObjectMapper();
        EnderecoDTO endereco = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
        return endereco;
    }

    public List<PacienteDTO> buscarEnderecoPorPaciente(int id) {
        EnderecoEntity endereco = enderecoRepository.findById(id).get();
        List<PacienteEntity> pacienteEntities = endereco.getPacienteEntities();
        return null;
    }
}
