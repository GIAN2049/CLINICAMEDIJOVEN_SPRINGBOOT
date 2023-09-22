
package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.service.CategoriaService;
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
public class CategoriaControlllers {

	@Autowired
	private CategoriaService service;
	
	@GetMapping("/categorias")
	public String categorias(Model model) {
		List<Categoria> obj = service.listarTodos();
		model.addAttribute("categorias", obj);
		return "ListCategorias";
	}
	
	@GetMapping("/categoria")
	public String viewFormCategoria() {
		return "AddCategoria";
	}
	
	@PostMapping("/categoria/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarCategoria(Categoria obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Categoria objCategoria = service.guardar(obj);
		if(objCategoria != null) {
			map.put("MENSAJE", "Se registro nueva categoria");
		}else {
			map.put("MENSAJE", "Error al registrar categoria");
		}
		return map;
	}
	
	
	@GetMapping("/buscarCategoria/{id}")
	@ResponseBody
	public Categoria buscar(@PathVariable int id) {
		Categoria objCategoria = service.buscarPorId(id);		
		return objCategoria;
	}
	
	@PutMapping("/categoria/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarCategoria(@PathVariable int id, Categoria obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Categoria objCategoria = service.buscarPorId(id);
		if(objCategoria!=null) {
			service.guardar(obj);
			map.put("MENSAJE", "Se actualizo categoria");
		}else {
			map.put("MENSAJE", "Error al actualizar categoria");
		}
		
		return map;
	}
	
	@DeleteMapping("/categoria/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarCategoria(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			service.eliminar(id);
			map.put("MENSAJE", "Categoria Eliminada");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar Categoria");
		}
		return map;
	}



}
