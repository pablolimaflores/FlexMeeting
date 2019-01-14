package com.projeto.flexmeeting.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.projeto.flexmeeting.domain.entity.Tipo;

/**
 * 
 * @author Pablo
 *
 */
@Service
public class InitApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitApplicationService.class);
    
    @Autowired
    TipoService tipoService;
    
    /**
     * Inicializa o sistema com alguns cadastros, caso não existam registros na tabela
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeTestData() {
    	LOGGER.info("Initialize test data");
    	
    	Tipo informativo = new Tipo("Informativo", "Deve apresentar tema relativo a decisão em outras instâncias e que afetam direta ou indiretamente os envolvidos.", false);
    	Tipo deliberativo = new Tipo("Deliberativo", "Deve apresentar o tema de consulta aos integrantes, devendo considerar tempo extra para debate, para que todos os integrantes possam tenham o mesmo tempo para se pronunciar.", true);
    	Tipo brainstorm = new Tipo("Brainstorm", "Idealmente deve ser uma reunião com poucos ou apenas um tema de pauta, dividido em duas partes, apresentação de ideias (todas as ideias são válidas, até as mais absurdas e devem ser anotadas pelo secretario) e debate das ideiais, onde as devem ser aprimoradas ou descartadas.", true);
    	Tipo trabalho = new Tipo("Trabalho", "Focada na realização de uma atividade ou demanda em tempo determinado, idealmente 2hs.", true);
    	
    	if (tipoService.countTipos() == 0) {
    		tipoService.insertTipo(informativo);
	        tipoService.insertTipo(deliberativo);
	        tipoService.insertTipo(brainstorm);
	        tipoService.insertTipo(trabalho);		            	
    	}
    	
//    	TipoParticipante solicitante = new TipoParticipante("Solicitante", "Pessoa responsável por agendar e informar todos os dados necessários para marcar a reunião.");
//    	TipoParticipante mediador = new TipoParticipante("Mediador", "Quem conduzirá a reunião (poderá ser a mesma pessoa que solicitou a reunião ou não). Engloba a funções de facilitador e colaborador.");
//    	TipoParticipante secretario = new TipoParticipante("Secretário", "Possui a função de anotador e é responsável por registrar sobre o andamento da reunião e fazer a ata de reunião.");
//    	TipoParticipante integrante = new TipoParticipante("Integrante", "Demais pessoas que irão compor a reunião e discutir sobre a mesma.");
//    	
//    	if (tipoParticipanteService.countTiposParticipante() == 0) {
//    		tipoParticipanteService.insertTipoParticipante(solicitante);
//	        tipoParticipanteService.insertTipoParticipante(mediador);
//	        tipoParticipanteService.insertTipoParticipante(secretario);
//	        tipoParticipanteService.insertTipoParticipante(integrante);
//    	}    	
    	    	
    	LOGGER.info("\n******** Initialization completed ********\n");
    }

}
