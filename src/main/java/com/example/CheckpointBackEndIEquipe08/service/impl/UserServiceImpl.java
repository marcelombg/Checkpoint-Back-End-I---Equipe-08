package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.UserEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.UserDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Boolean create(UserDTO userDTO){
        UserEntity userEntity = new UserEntity(userDTO);
        String password = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);

        try{
            userRepository.save((userEntity));
        }catch(Exception e){
            return null;
        }

        return true;
    }
}
