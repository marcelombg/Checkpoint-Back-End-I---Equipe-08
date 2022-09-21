package com.example.CheckpointBackEndIEquipe08.service;

import com.example.CheckpointBackEndIEquipe08.exception.NotFoundException;

import java.util.List;

public interface IService<T> {

    T registrar(T t) throws NotFoundException;
    List<T> buscarTodos();
    String excluir(Integer id);
    T modificar(T t, int id);
    T buscarID(int id) throws NotFoundException;
}
