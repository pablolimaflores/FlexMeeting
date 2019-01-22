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
import com.projeto.flexmeeting.domain.entity.Pessoa;
import com.projeto.flexmeeting.domain.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	
	@Autowired
	private PessoaService service;

	/**
	 * Direciona para a página de cadastro passando o objeto da entidade por parâmetro
	 * @param pessoa
	 * @return
	 */
	@GetMapping("/cadastrar")
	public String cadastrar(Pessoa pessoa) {
		return "pessoa/cadastro";
	}
	
    /**
	 * Método para listagem de todas as pessoas cadastrados no sistema, em formato de paginação
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("/listar")
	public String listar(ModelMap model, @PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		Page<Pessoa> page = service.findAllPessoas(pageable);		
		model.addAttribute("page", page);
		model.addAttribute("pessoas", page.getContent());
		return "pessoa/lista"; 
	}
	
	/**
	 * Método que persiste o registro de pessoa na base de dados
	 * @param pessoa
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/salvar")
	public String salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "pessoa/cadastro";
		}
		
		service.insertPessoa(pessoa);
		attr.addFlashAttribute("success", "Pessoa cadastrada com sucesso.");
		return "redirect:/pessoas/listar";
	}
	
	/**
	 * Método que carrega a página de cadastro, juntamente com o objeto a ser editado
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("pessoa", service.findPessoaById(id));
		return "pessoa/cadastro";
	}
	
	/**
	 * Método que atualiza o registro no banco de dados
	 * @param pessoa
	 * @param result
	 * @param attr
	 * @return
	 */
	@PostMapping("/editar")
	public String editar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "pessoa/cadastro";
		}
		
		service.updatePessoa(pessoa);
		attr.addFlashAttribute("success", "Pessoa editada com sucesso.");
		return "redirect:/pessoas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, 
			@PageableDefault(FlexMeetingApplication.TABLE_MAX_ROWS) Pageable pageable ) {
		
		if (service.pessoaTemParticipantesAssociados(id) || service.pessoaTemReunioesAssociadas(id)) {
			model.addAttribute("fail", "Pessoa não removida. Possui outros registros vinculado(s).");
		} else {
			service.deletePessoa(id);
			model.addAttribute("success", "Pessoa excluída com sucesso.");
		}
		
		return listar(model, pageable);
	}
	
}
