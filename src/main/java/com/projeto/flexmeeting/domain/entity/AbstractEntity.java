package com.projeto.flexmeeting.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe utilizada como entidade 'pai' sendo herdada por todas as outras
 * classes.
 * 
 *
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = AbstractEntity.class)
public abstract class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2227201546998331220L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	protected Long id;

	/**
	 * 
	 */
	@Column(nullable = false, updatable = false)
	protected LocalDateTime created;

	/**
	 * 
	 */
	@Column(nullable = false)
	protected LocalDateTime updated;

	@Column()
	private Boolean ativo;

	/**
	 * 
	 * @param id
	 */
	public AbstractEntity(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 */
	public AbstractEntity(Long id, Boolean ativo) {
		this.id = id;
		this.ativo = ativo;
	}

	/**
	 * 
	 */
	@PrePersist
	public void refreshCreatedAndUpdated() {
		final LocalDateTime now = LocalDateTime.now();
		this.created = now;
		this.updated = now;
	}

	/**
	 * 
	 */
	@PreUpdate
	public void refreshUpdated() {
		this.updated = LocalDateTime.now();
	}	

}