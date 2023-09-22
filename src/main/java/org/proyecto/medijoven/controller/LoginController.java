package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.service.DatosHomeDashboardServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
import org.proyecto.medijoven.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"ENLACES","USUARIO"})
@Controller
public class LoginController {
	
	@Autowired
	private UsuarioServiceImpl servicioUsuario;
	
	@Autowired
	private DatosHomeDashboardServiceImpl service;
	@Autowired
	private PacienteServiceImpl servicePaciente;
	
	@GetMapping("/login")
	public String formLogin() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, Authentication auth) {
		//OBTENER NOMBRE DEL USUARIO
		String username = auth.getName();
		Usuario u = servicioUsuario.loginUsuario(username);
		List<Menu> lista = servicioUsuario.traerMenus(u.getRol().getId());
		
		
		Long countMedicos = service.obtenerCantidadMedicos();
	    Long countPacientes = service.obtenerCantidadPacientes();
	    List<Paciente> pacientes = servicePaciente.UltimosPacientes();

	    model.addAttribute("ENLACES", lista);
	    //
	    
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
	}
}
