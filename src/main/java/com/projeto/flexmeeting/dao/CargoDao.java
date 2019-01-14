package com.projeto.flexmeeting.dao;

import java.util.List;

import com.projeto.flexmeeting.domain.entity.Cargo;

public interface CargoDao {

    void save(Cargo cargo );

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
}
