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
@RequestMapping("medijoven/dashboard")
public class EspecialidadController {
	
	
	@Autowired
	private EspecialidadServiceImpl service;
	
	@GetMapping("/especialidades")
	public String ListaEspecialidad(Model model) {
		List<Especialidad> especialidades = service.listarTodos();
		model.addAttribute("especialidades", especialidades);
		return "ListEspecialidades";
	}
	
	@GetMapping("/especialidad")
	public String viewFormEspecialidad() {
		return "AddEspecialidad";
	}
	
	@PostMapping("/especialidad/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarEspecialidad(Especialidad obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Especialidad objEspecialidad = service.guardar(obj);
		if(objEspecialidad != null) {
			map.put("MENSAJE", "Se registro nueva Especialidad");
		}else {
			map.put("MENSAJE", "Error al registrar Especialidad");
		}
		return map;
	}
	
	
	@GetMapping("/buscar/especialidad/{id}")
	@ResponseBody
	public Especialidad buscar(@PathVariable int id) {
		Especialidad objEspecialidad = service.buscarPorId(id);		
		return objEspecialidad;
	}
	
	@PutMapping("/especialidad/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarEspecialidad(@PathVariable int id, Especialidad obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Especialidad objEspecialidad = service.buscarPorId(id);
		if(objEspecialidad!=null) {
			service.guardar(obj);
			map.put("MENSAJE", "Se actualizo Especialidad");
		}else {
			map.put("MENSAJE", "Error al actualizar Especialidad");
		}
		
		return map;
	}
	
	@DeleteMapping("/especialidad/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarEspecialidad(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			service.eliminar(id);
			map.put("MENSAJE", "Especialidad Eliminada");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar Especialidad");
		}
		return map;
	}
	

	
}
