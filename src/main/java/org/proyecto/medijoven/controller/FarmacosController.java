package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.service.CategoriaService;
import org.proyecto.medijoven.service.FarmacoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/medijoven/dashboard")
public class FarmacosController {
	
	@Autowired
	private CategoriaService servicioCategoria;
	
	@Autowired
	private FarmacoServiceImpl servicioFarmaco;
	
	@GetMapping("/farmacos")
	public String listadoFarmacos(Model model) {
		List<Farmaco> farmacos = servicioFarmaco.listarTodos();
		List<Categoria> categorias = servicioCategoria.listarTodos();
		model.addAttribute("farmacos", farmacos);
		model.addAttribute("categorias", categorias);
		return "ListFarmacos";
	}

	@GetMapping("/farmaco")
	public String viewFormFarmaco(Model model) {
		List<Categoria> categorias = servicioCategoria.listarTodos();
		model.addAttribute("categorias", categorias);
		return "AddFarmaco";
	}
	
	@PostMapping("/farmaco/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarCategoria(Farmaco obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Farmaco objFarmaco = servicioFarmaco.guardar(obj);
		if(objFarmaco != null) {
			map.put("MENSAJE", "Se registro nueva categoria");
		}else {
			map.put("MENSAJE", "Error al registrar categoria");
		}
		return map;
	}
	
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Farmaco buscar(@PathVariable int id) {
		Farmaco objFarmaco = servicioFarmaco.buscarPorId(id);		
		return objFarmaco;
	}
	
	@PutMapping("/farmaco/actualizar/{id}")
	@ResponseBody
	public HashMap<String, String> actualizarFarmaco(@PathVariable int id, Farmaco obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Farmaco objFarmaco = servicioFarmaco.buscarPorId(id);
		if(objFarmaco != null) {
			servicioFarmaco.guardar(obj);
			map.put("MENSAJE", "Se actualizo farmaco");
		}else {
			map.put("MENSAJE", "Error al actualizar farmaco");
		}
		
		return map;
	}
	
	
	@DeleteMapping("/farmaco/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarCategoria(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			servicioFarmaco.eliminar(id);
			map.put("MENSAJE", "Categoria Eliminada");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar Categoria");
		}
		return map;
	}
	
	
}
