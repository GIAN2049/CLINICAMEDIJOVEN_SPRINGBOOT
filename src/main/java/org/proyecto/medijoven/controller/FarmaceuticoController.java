package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Farmaceutico;
import org.proyecto.medijoven.service.FarmaceuticoService;
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
public class FarmaceuticoController {

	@Autowired
	private FarmaceuticoService servicio;
	
	@GetMapping("/farmaceuticos")
	public String ListMedicos(Model model) {
		List<Farmaceutico> farmaceuticos = servicio.listarTodos();
		model.addAttribute("farmaceuticos", farmaceuticos);
		return "ListFarmaceuticos";
	}

	@GetMapping("/farmaceutico")
	public String viewFormFarmaceutico() {
		return "AddFarmaceutico";
	}

	@PostMapping("/farmaceutico/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarFarmaceutico(Farmaceutico empleado) {
		HashMap<String, String> map = new HashMap<String, String>();
		Farmaceutico objEspecialidad = servicio.guardar(empleado);
		if (objEspecialidad != null) {
			map.put("MENSAJE", "Se Añadio un nuevo Registro");
		} else {
			map.put("MENSAJE", "Error al añadir nuevo Registro");
		}
		return map;
	}
	
	@GetMapping("/buscar/farmaceutico/{id}")
	@ResponseBody
	public Farmaceutico buscar(@PathVariable int id) {
		Farmaceutico objEspecialidad = servicio.buscarPorId(id);		
		return objEspecialidad;
	}
	
	@PutMapping("/farmaceutico/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarFarmaceutico(@PathVariable int id, Farmaceutico obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Farmaceutico objEspecialidad = servicio.buscarPorId(id);
		if(objEspecialidad!=null) {
			servicio.guardar(obj);
			map.put("MENSAJE", "Se actualizo el registro con id: " + obj.getIdFarmeceutico());
		}else {
			map.put("MENSAJE", "Error al actualizar registro con id: " + obj.getIdFarmeceutico());
		}
		
		return map;
	}
	
	@DeleteMapping("/farmaceutico/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarFarmaceutico(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			servicio.eliminar(id);
			map.put("MENSAJE", "Se elimino registro con id: " + id);
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al eliminar registro con id: " + id);
		}
		return map;
	}

}
