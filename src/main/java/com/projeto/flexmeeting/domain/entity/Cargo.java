package com.projeto.flexmeeting.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "CARGOS")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude="funcionarios")
public class Cargo extends AbstractEntity {

	private static final long serialVersionUID = 2295386205889544370L;

	@NotBlank(message = "O nome do cargo é obrigatório.")
	@Size(max = 60, message = "O nome do cargo deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	@NotNull(message = "Selecione o departamento relativo ao cargo.")
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

}
