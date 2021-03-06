package com.projeto.flexmeeting.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.flexmeeting.domain.entity.PontoPauta;
import com.projeto.flexmeeting.domain.repository.IPontoPautaRepository;

@Service
public class PontoPautaService {
	
	@Autowired
	private IPontoPautaRepository pontoPautaRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PontoPauta> findAllPontoPautas() {
        return this.pontoPautaRepository.findAll();
    }
	
	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public Page<PontoPauta> findAllPontoPautasPageable(Pageable pageable){
		return pontoPautaRepository.findAll(pageable);
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public PontoPauta findPontoPautaById( long id ) {
		
		final PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de PontoPauta com id "+ id + " não encontrado." ) );
		return pontoPautaSaved;
    }
	
	/**
	 * 
	 * @param pontoPauta
	 * @return
	 */
	public PontoPauta insertPontoPauta( PontoPauta pontoPauta ) {
    	
		pontoPauta.setAtivo(true);
		pontoPauta.refreshCreatedAndUpdated();		
		return this.pontoPautaRepository.save( pontoPauta );
	}
	/**
	 * 
	 * @param idReuniao
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PontoPauta> listPontoPautaByReuniaoId( long idReuniao ) {
		
		return this.pontoPautaRepository.listByReuniaoId(idReuniao);
    }	
	/**
	 * 
	 * @param pontoPauta
	 * @return
	 */
	public PontoPauta updatePontoPauta( PontoPauta pontoPauta ) {
		
		this.pontoPautaRepository.findById(pontoPauta.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de PontoPauta com id "+pontoPauta.getId() + " não encontrado." ) );
		pontoPauta.refreshUpdated();
		return this.pontoPautaRepository.saveAndFlush( pontoPauta );
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deletePontoPauta( long id ) {
		
		final PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de PontoPauta com id "+ id + " não encontrado." ) );
		
		this.pontoPautaRepository.deleteById( pontoPautaSaved.getId() );
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public PontoPauta updateStatusPontoPauta( long id ) {
		
		PontoPauta pontoPautaSaved = this.pontoPautaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "PontoPauta não encontrado." ));		 
		
		pontoPautaSaved.setAtivo( !pontoPautaSaved.getAtivo() );
		
		return this.pontoPautaRepository.saveAndFlush( pontoPautaSaved );
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countPontoPauta() {
		return this.pontoPautaRepository.count();
	}
}


