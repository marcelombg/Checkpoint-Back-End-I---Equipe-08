package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.UserEntity;
import com.example.CheckpointBackEndIEquipe08.enums.UserRoles;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserDTO {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    public UserDTO() {
    }

    /*public UserDTO(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.userRoles = userEntity.getUserRoles();
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
