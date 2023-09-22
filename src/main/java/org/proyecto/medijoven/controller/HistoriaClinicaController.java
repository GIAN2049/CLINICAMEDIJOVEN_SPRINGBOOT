package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.HistoriaClinica;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Receta;
import org.proyecto.medijoven.service.HistoriaClinicaServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
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
public class HistoriaClinicaController {
	
	
	@Autowired
	private HistoriaClinicaServiceImpl serviceHistoriaClinica;
	
	@Autowired
	private PacienteServiceImpl servicePaciente;
	
	@GetMapping("/historias-clinicas")
	public String ListaHistoriasClinicas(Model model) {
		List<HistoriaClinica> historiaClinicas = serviceHistoriaClinica.listarTodos();
		List<Paciente> pacientes = servicePaciente.listarTodos();

		model.addAttribute("historiasClinicas", historiaClinicas);
		model.addAttribute("pacientes", pacientes);
		
		return "ListHistoriaClinicas";
	}

	@GetMapping("/historia-clinica")
	public String viewFormHistoriaClinica(Model model) {
		List<Paciente> pacientes = servicePaciente.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "AddHistoriaClinica";
	}

	@PostMapping("/historia-clinica/registrar")
	@ResponseBody 
	public HashMap<String, String> registrarReceta(HistoriaClinica obj) {
		HashMap<String, String> response = new HashMap<>();

		try {
			HistoriaClinica objHClinica = serviceHistoriaClinica.guardarHistoriaClinica(obj);
			if (objHClinica != null) {
				response.put("MENSAJE", "Se registró una nueva Historia clinica con ID " + objHClinica.getIdHistoriaClinica());
			} else {
				response.put("MENSAJE", "Error al registrar Historia clinica");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			response.put("MENSAJE", "Error al registrar Historia clinica: " + e.getMessage());
		}

		return response;
	}

	@GetMapping("/buscar/historia-clinica/{id}")
	@ResponseBody
	public HistoriaClinica buscar(@PathVariable int id) {
		HistoriaClinica objHClinica = serviceHistoriaClinica.obtenerHistoriaClinicaPorId(id);
		return objHClinica;
	}

	@GetMapping("/buscar/historia-clinica/paciente/{id}")
	@ResponseBody
	public List<HistoriaClinica> buscarFarmacoPorPaciente(@PathVariable int id) {
		List<HistoriaClinica> objHClinica = serviceHistoriaClinica.obtenerHistoriasClinicasPorPaciente(id);
		return objHClinica;
	}

	@PutMapping("/historia-clinica/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarReceta(@PathVariable int id, HistoriaClinica obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		HistoriaClinica objHClinica = serviceHistoriaClinica.obtenerHistoriaClinicaPorId(id);
		if (objHClinica != null) {
			serviceHistoriaClinica.guardarHistoriaClinica(obj);
			map.put("MENSAJE", "Se actualizo Historia clinica");
		} else {
			map.put("MENSAJE", "Error al actualizar Historia clinica");
		}

		return map;
	}

	@DeleteMapping("/historia-clinica/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarReceta(@PathVariable int id) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			serviceHistoriaClinica.eliminarHistoriaClinica(id);
			map.put("MENSAJE", "Historia clinica Eliminada");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar Historia clinica");
		}
		return map;
	}
}
