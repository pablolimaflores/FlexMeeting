package com.projeto.flexmeeting.dao;

import java.util.List;

import com.projeto.flexmeeting.domain.entity.Departamento;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();
}
