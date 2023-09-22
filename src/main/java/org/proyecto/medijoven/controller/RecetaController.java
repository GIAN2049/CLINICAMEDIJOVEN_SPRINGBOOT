package org.proyecto.medijoven.controller;
import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Receta;
import org.proyecto.medijoven.service.CategoriaServiceImpl;
import org.proyecto.medijoven.service.EspecialidadServiceImpl;
import org.proyecto.medijoven.service.FarmacoServiceImpl;
import org.proyecto.medijoven.service.MedicoServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
import org.proyecto.medijoven.service.RecetaServiceImpl;
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
public class RecetaController {

	@Autowired
	private RecetaServiceImpl service;
	@Autowired
	private PacienteServiceImpl servicePaciente;
	@Autowired
	private MedicoServiceImpl serviceMedico;
	@Autowired
	private EspecialidadServiceImpl serviceEspecialidad;
	@Autowired
	private CategoriaServiceImpl serviceCategoria;
	@Autowired
	private FarmacoServiceImpl serviceFarmaco;

	@GetMapping("/recetas")
	public String ListaMedico(Model model) {
		List<Receta> recetas = service.listarTodos();
		List<Paciente> pacientes = servicePaciente.listarTodos();
		List<Medico> medicos = serviceMedico.listarTodos();
		List<Especialidad> especialidades = serviceEspecialidad.listarTodos();
		List<Categoria> categorias = serviceCategoria.listarTodos();
		List<Farmaco> farmacos = serviceFarmaco.listarTodos();

		model.addAttribute("recetas", recetas);
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("medicos", medicos);
		model.addAttribute("especialidades", especialidades);
		model.addAttribute("categorias", categorias);
		model.addAttribute("farmacos", farmacos);
		return "ListRecetas";
	}

	@GetMapping("/receta")
	public String viewFormMedico(Model model) {
		List<Paciente> pacientes = servicePaciente.listarTodos();
		List<Medico> medicos = serviceMedico.listarTodos();
		List<Especialidad> especialidades = serviceEspecialidad.listarTodos();
		List<Categoria> categorias = serviceCategoria.listarTodos();
		List<Farmaco> farmacos = serviceFarmaco.listarTodos();

		model.addAttribute("pacientes", pacientes);
		model.addAttribute("medicos", medicos);
		model.addAttribute("especialidades", especialidades);
		model.addAttribute("categorias", categorias);
		model.addAttribute("farmacos", farmacos);
		return "AddReceta";
	}

	@PostMapping("/receta/registrar")
	@ResponseBody
	public HashMap<String, String> registrarReceta(Receta obj) {
		HashMap<String, String> response = new HashMap<>();

		try {
			Receta nuevaReceta = service.guardar(obj);
			if (nuevaReceta != null) {
				response.put("MENSAJE", "Se registró una nueva receta médica con ID " + nuevaReceta.getIdReceta());
			} else {
				response.put("MENSAJE", "Error al registrar la receta médica");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			response.put("MENSAJE", "Error al registrar la receta médica: " + e.getMessage());
		}

		return response;
	}

	@GetMapping("/buscar/receta/{id}")
	@ResponseBody
	public Receta buscar(@PathVariable int id) {
		Receta objReceta = service.buscarPorId(id);
		return objReceta;
	}

	@GetMapping("/buscar/farmaco/categoria/{id}")
	@ResponseBody
	public List<Farmaco> buscarFarmacoPorCategoria(@PathVariable int id) {
		List<Farmaco> objFarmaco = serviceCategoria.buscarFarmacoPorIdCategoria(id);
		return objFarmaco;
	}

	@PutMapping("/receta/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarReceta(@PathVariable int id, Receta obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		Receta objReceta = service.buscarPorId(id);
		if (objReceta != null) {
			service.guardar(obj);
			map.put("MENSAJE", "Se actualizo receta medica");
		} else {
			map.put("MENSAJE", "Error al actualizar receta medica");
		}

		return map;
	}

	@DeleteMapping("/receta/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarReceta(@PathVariable int id) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			service.eliminar(id);
			map.put("MENSAJE", "receta medica Eliminada");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar receta medica");
		}
		return map;
	}

}
