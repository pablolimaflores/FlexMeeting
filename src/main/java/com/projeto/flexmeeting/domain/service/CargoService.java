package com.projeto.flexmeeting.domain.service;

import java.util.List;

import com.projeto.flexmeeting.domain.entity.Cargo;

public interface CargoService {

	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
	
	boolean cargoTemFuncionarios(Long id);
}
