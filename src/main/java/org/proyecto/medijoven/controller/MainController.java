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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@SessionAttributes({"ENLACES","USUARIO", "INFOPACIENT"})
@Controller
public class MainController {
	
	@Autowired
	private UsuarioServiceImpl servicioUsuario;
	
	@Autowired
	private DatosHomeDashboardServiceImpl service;
	@Autowired
	private PacienteServiceImpl servicePaciente;
	
	@Autowired
	private PacienteServiceImpl serviceP;
	
	@GetMapping("/login")
	public String formLogin() {
		return "login";
	}
	
	@GetMapping("/create-account")
	public String formCreateAccount() {
		return "RegisterPacient";
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarPaciente(Paciente obj, @RequestParam("password") String password){
		HashMap<String, String> map = new HashMap<String, String>();
		Paciente objPaciente = serviceP.guardar(obj, password);
		
		if(objPaciente != null) {
			map.put("MENSAJE", "Se registro nuevo paciente");
		}else {
			map.put("MENSAJE", "Error al registrar paciente");
		}
		return map;
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, Authentication auth, HttpSession session) {
		//OBTENER NOMBRE DEL USUARIO
		String username = auth.getName();
		Usuario u = servicioUsuario.loginUsuario(username);
		List<Menu> lista = servicioUsuario.traerMenus(u.getRol().getIdRol());
		
		
		Long countMedicos = service.obtenerCantidadMedicos();
	    Long countPacientes = service.obtenerCantidadPacientes();
	    List<Paciente> pacientes = servicePaciente.UltimosPacientes();
	    Paciente datosPaciente = servicioUsuario.traerPacientePorUsuario(u.getId());

	    model.addAttribute("ENLACES", lista);
	    model.addAttribute("USUARIO", username);
	    if (datosPaciente != null) {
	        model.addAttribute("INFOPACIENT", datosPaciente.getId());
	    } else {
	        
	        model.addAttribute("INFOPACIENT", "No disponible");
	    }
	    
	    
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
