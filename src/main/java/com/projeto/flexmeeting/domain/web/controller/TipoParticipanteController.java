package com.projeto.flexmeeting.domain.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.flexmeeting.FlexMeetingApplication;
import com.projeto.flexmeeting.domain.entity.TipoParticipante;
import com.projeto.flexmeeting.domain.service.TipoParticipanteService;

@Controller
@RequestMapping("/tiposParticipante")
public class TipoParticipanteController {

	
	@Autowired
	private TipoParticipanteService service;

	/**
	 * Direciona para a página de cadastro passando o objeto da entidade por parâmetro
	 * @param tipoParticipante
	 * @return
	 */
	@GetMapping("/cadastrar")
	public String cadastrar(TipoParticipante tipoParticipante) {
		return "tipoParticipante/cadastro";
	}
	
    /**
	 * Método para listagem de todas os tipos de participante cadastrados no sistema, em formato de paginação
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("/listar")
	public String listar(ModelMap model, @PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		Page<TipoParticipante> page = service.findAllTiposParticipantePage(pageable);		
		model.addAttribute("page", page);
		model.addAttribute("tiposParticipante", page.getContent());
		return "tipoParticipante/lista"; 
	}
	
	/**
	 * Método que persiste o registro de tipoParticipante na base de dados
	 * @param tipoParticipante
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/salvar")
	public String salvar(@Valid TipoParticipante tipoParticipante, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "tipoParticipante/cadastro";
		}
		
		service.insertTipoParticipante(tipoParticipante);
		attr.addFlashAttribute("success", "Tipo de participante inserido com sucesso.");
		return "redirect:/tiposParticipante/listar";
	}
	
	/**
	 * Método que carrega a página de cadastro, juntamente com o objeto a ser editado
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tipoParticipante", service.findTipoParticipanteById(id));
		return "tipoParticipante/cadastro";
	}
	
	/**
	 * Método que atualiza o registro no banco de dados
	 * @param tipoParticipante
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/editar")
	public String editar(@Valid TipoParticipante tipoParticipante, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "tipoParticipante/cadastro";
		}
		
		service.updateTipoParticipante(tipoParticipante);
		attr.addFlashAttribute("success", "Tipo de Participante editado com sucesso.");
		return "redirect:/tiposParticipante/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, 
			@PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		
		if (service.tipoTemParticipantesAssociados(id)) {
			model.addAttribute("fail", "Tipo de Participante não removido. Possui participantes(s) vinculado(s).");
		} else {
			service.deleteTipoParticipante(id);
			model.addAttribute("success", "Tipo de Participante excluído com sucesso.");
		}
		
		return listar(model, pageable);
	}
	
}
