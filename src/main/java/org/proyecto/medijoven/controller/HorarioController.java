package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Horario;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.service.HorarioService;
import org.proyecto.medijoven.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("medijoven/dashboard")
public class HorarioController {
	
	
	@Autowired
	private HorarioService servicio;
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping("/horarios")
	public String ListaEspecialidad(Model model) {
		List<Horario> horarios = servicio.listarTodos();
		List<Medico> medicos = medicoService.listarTodos();
		model.addAttribute("horarios", horarios);
		model.addAttribute("medicos", medicos);
		return "ListHorarios";
	}
	
	@GetMapping("/horario")
	public String viewFormEspecialidad(Model model) {
		List<Medico> medicos = medicoService.listarTodos();
		model.addAttribute("medicos", medicos);
		return "AddHorario";
	}
	
	@PostMapping("/horario/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarEspecialidad(Horario obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Horario objHorario = servicio.guardar(obj);
		if(objHorario != null) {
			map.put("MENSAJE", "Se registro un nuevo Horario");
		}else {
			map.put("MENSAJE", "Error al registrar Horario");
		}
		return map;
	}
	
	
	@GetMapping("/buscar/horario/{id}")
	@ResponseBody
	public Horario buscar(@PathVariable int id) {
		Horario objHorario = servicio.buscarPorId(id);		
		return objHorario;
	}
	
	@PutMapping("/horario/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarEspecialidad(@PathVariable int id, Horario obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Horario objHorario = servicio.buscarPorId(id);
		if(objHorario!=null) {
			servicio.guardar(obj);
			map.put("MENSAJE", "Se actualizo Horario");
		}else {
			map.put("MENSAJE", "Error al actualizar Horario");
		}
		
		return map;
	}
	
	@DeleteMapping("/horario/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarEspecialidad(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			servicio.eliminar(id);
			map.put("MENSAJE", "Horario Eliminado");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar Horario");
		}
		return map;
	}
}
