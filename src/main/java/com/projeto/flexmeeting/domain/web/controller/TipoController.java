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
import com.projeto.flexmeeting.domain.entity.Tipo;
import com.projeto.flexmeeting.domain.service.TipoService;

@Controller
@RequestMapping("/tipos")
public class TipoController {

	
	@Autowired
	private TipoService service;

	/**
	 * Direciona para a página de cadastro passando o objeto da entidade por parâmetro
	 * @param tipo
	 * @return
	 */
	@GetMapping("/cadastrar")
	public String cadastrar(Tipo tipo) {
		return "tipo/cadastro";
	}
	
    /**
	 * Método para listagem de todas os tipos de reuniões cadastradas no sistema, em formato de paginação
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("/listar")
	public String listar(ModelMap model, @PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		Page<Tipo> page = service.findAllTipos(pageable);		
		model.addAttribute("page", page);
		model.addAttribute("tipos", page.getContent());
		return "tipo/lista"; 
	}
	
	/**
	 * Método que persiste o registro de tipo na base de dados
	 * @param tipo
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/salvar")
	public String salvar(@Valid Tipo tipo, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "tipo/cadastro";
		}
		
		service.insertTipo(tipo);
		attr.addFlashAttribute("success", "Tipo inserido com sucesso.");
		return "redirect:/tipos/listar";
	}
	
	/**
	 * Método que carrega a página de cadastro, juntamente com o objeto a ser editado
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("tipo", service.findTipoById(id));
		return "tipo/cadastro";
	}
	
	/**
	 * Método que atualiza o registro no banco de dados
	 * @param tipo
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/editar")
	public String editar(@Valid Tipo tipo, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "tipo/cadastro";
		}
		
		service.updateTipo(tipo);
		attr.addFlashAttribute("success", "Tipo editado com sucesso.");
		return "redirect:/tipos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, 
			@PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		
		if (service.tipoTemReunioesOuPautasAssociadas(id)) {
			model.addAttribute("fail", "Tipo não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.deleteTipo(id);
			model.addAttribute("success", "Tipo excluído com sucesso.");
		}
		
		return listar(model, pageable);
	}
	
}
