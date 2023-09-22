package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.service.EspecialidadServiceImpl;
import org.proyecto.medijoven.service.MedicoServiceImpl;
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
@RequestMapping("/medijoven/dashboard")
public class MedicoController {
	
	
	@Autowired
	private MedicoServiceImpl service;
	
	@GetMapping("/medicos")
	public String ListaMedico(Model model) {
		List<Medico> medicos = service.listarTodos();
		model.addAttribute("medicos", medicos);
		return "ListMedicos";
	}
	
	@GetMapping("/medico")
	public String viewFormMedico() {
		return "AddMedico";
	}
	
	@PostMapping("/medico/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarMedico(Medico obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Medico objEspecialidad = service.guardar(obj);
		if(objEspecialidad != null) {
			map.put("MENSAJE", "Se registro nuevo medico");
		}else {
			map.put("MENSAJE", "Error al registrar medico");
		}
		return map;
	}
	
	
	@GetMapping("/buscar/medico/{id}")
	@ResponseBody
	public Medico buscar(@PathVariable int id) {
		Medico objEspecialidad = service.buscarPorId(id);		
		return objEspecialidad;
	}
	
	@PutMapping("/medico/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarMedico(@PathVariable int id, Medico obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Medico objEspecialidad = service.buscarPorId(id);
		if(objEspecialidad!=null) {
			service.guardar(obj);
			map.put("MENSAJE", "Se actualizo medico");
		}else {
			map.put("MENSAJE", "Error al actualizar medico");
		}
		
		return map;
	}
	
	@DeleteMapping("/medico/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarMedico(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			service.eliminar(id);
			map.put("MENSAJE", "medico Eliminado");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar medico");
		}
		return map;
	}
	

	
}
