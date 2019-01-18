package com.projeto.flexmeeting.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TipoParticipante extends AbstractEntity {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -3701813693477289761L;
	
	/**
	 * 
	 */
	@NotBlank(message ="{NotBlank.tipoParticipante.nome}")
	@Column(nullable = false, length = 50, unique = true)
	@Size(min=3, max=50, message = "{Size.tipoParticipante.nome}")
	private String nome;
	
	/**
	 * 
	 */
	@Column(length=500)
	@Size(max=500,  message = "{Size.tipo.descricaoParticipante}")
	private String descricao;
		
    /**
     *  
     * @param nome
     * @param descricao
     * @param debate
     */
	public TipoParticipante(String nome, String descricao) {			
		this.nome = nome;
		this.descricao = descricao;		
	}
    
    /**
     * 
     * @param id
     * @param nome
     * @param descricao
     * @param debate
     */
	public TipoParticipante(Long id, String nome, String descricao) {
		super(id);		
		this.nome = nome;
		this.descricao = descricao;		
	}
	
	/**
	 * 
	 * @param id
	 */
	public TipoParticipante(Long id) {
		super(id);
	}
        		
}
