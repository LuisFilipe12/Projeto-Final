package br.com.luissenai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.luissenai.model.Paciente;
import br.com.luissenai.repositories.PacienteRepository;

@Controller
public class PacienteController {
	
	PacienteRepository ar;
	//página inicial
	@RequestMapping("/")
	public String home() {
		return "Index";
	}
	
	//listar os pacientes
	
	@RequestMapping("/listarPacientes")
	public String listarPacientes(Model model) {
		model.addAttribute("pacientes", ar.findAll());
		return "listarPacientes";
	}
	
	@GetMapping("/add")
	public String cadastrarPacientes(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "cadastrarPaciente";
	}
	
	@PostMapping("/processarCadastro")
	public String processarCadastro(Paciente paciente, BindingResult br) {
		if(br.hasErrors()) {
			return "cadastrarPaciente";
		}
		ar.save(paciente);
		return "redirect:/listarPacientes";
	}
	
	//Alterar Paciente
	@RequestMapping(value = "/alterarPaciente/{id}", method = RequestMethod.GET )
	public ModelAndView alterarAluno(@PathVariable("id") Integer id) {
		Paciente paciente = ar.findById(id).get();
		ModelAndView mv = new ModelAndView("alterarPaciente");
		mv.addObject("paciente", paciente);
		return mv;
	}
	
	//Salvar o paciente alterado
	@RequestMapping(value = "/alterarPaciente/{id}", method = RequestMethod.POST)
	public String alterarPaciente(@Validated Paciente paciente) {
		ar.save(paciente);
		return "redirect:/listarPacientes";
	}
	
	//Deletar o Paciente
	@RequestMapping("/confirmarExclusaoPaciente")
	public ModelAndView confirmarExclusaoPaciente(@PathVariable ("id") Integer id) {
		Paciente paciente = ar.findById(id).get();
		ModelAndView mv = new ModelAndView("/excluirPaciente");
		mv.addObject("Paciente", paciente);
		return mv;
	}
	
	/*Confirmado a exclusçao e salvar no banco de dados*/
	@RequestMapping("excluirPacientes")
	public String excluirPaciente(Integer id) {
		Paciente paciente = ar.findById(id).get();
		ar.delete(paciente);
		return "redirect:/listarPacientes"; 	
	}
	
	
}

