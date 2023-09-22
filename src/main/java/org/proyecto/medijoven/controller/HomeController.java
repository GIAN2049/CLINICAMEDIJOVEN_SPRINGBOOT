package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.service.DatosHomeDashboardServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/medijoven/dashboard")
public class HomeController {

	/*@Autowired
	private DatosHomeDashboardServiceImpl service;
	@Autowired
	private PacienteServiceImpl servicePaciente;
	
	
	@GetMapping("/inicio")
	public String viewHome(Model model) {
		Long countMedicos = service.obtenerCantidadMedicos();
	    Long countPacientes = service.obtenerCantidadPacientes();
	    List<Paciente> pacientes = servicePaciente.UltimosPacientes();
	    
	    model.addAttribute("countMedicos", countMedicos);
	    model.addAttribute("countPacientes", countPacientes);
	    model.addAttribute("pacientes", pacientes);
		return "Homedashboard";
	}
	
	@GetMapping("/datos")
	@ResponseBody
	public Map<String, Long> datosPanel() {
	    Long countMedicos = service.obtenerCantidadMedicos();
	    Long countPacientes = service.obtenerCantidadPacientes();
	    
	    Map<String, Long> responseData = new HashMap<>();
	    responseData.put("countMedicos", countMedicos);
	    responseData.put("countPacientes", countPacientes);
	    
	    return responseData;
	}*/

	
}
