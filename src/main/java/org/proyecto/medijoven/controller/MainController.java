package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.service.DatosHomeDashboardServiceImpl;
import org.proyecto.medijoven.service.EspecialidadServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
import org.proyecto.medijoven.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

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
	
	@Autowired
	private EspecialidadServiceImpl serviceEsp;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@GetMapping("")
	public String MainPage(Model model) {
		
		List<Especialidad> especialidades = serviceEsp.listarTodos();
		model.addAttribute("especialidades", especialidades);

		return "MainPage";
	}
	
	@GetMapping("/cuenta/login")
	public String formLogin() {
		return "login";
	}
	
	@GetMapping("/cuenta/crear-cuenta")
	public String formCreateAccount() {
		return "RegisterPacient";
	}
	
	@Transactional
	@PostMapping("/cuenta/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarPaciente(Paciente obj,
					@RequestParam("username") String username, 
					@RequestParam("password") String password){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			
			serviceP.guardar(obj);

			Usuario user = new Usuario();
			user.setUsername(username);
			user.setPassword(password);
			Rol rol = new Rol();
			rol.setIdRol(4);
			user.setRol(rol);
			Paciente paciente = new Paciente();
			paciente.setId(obj.getId());
			user.setPaciente(paciente);
			
			
			System.out.println("usuario: " + user.getUsername());
			System.out.println("contrasena: " + user.getPassword());
			
			System.out.println("========================");
			
			System.out.println("usuario: " + username);
			System.out.println("contrasena: " + password);

			servicioUsuario.registrarUsuario(user);

			
			map.put("MENSAJE", "Cuenta Registrada");
			
		} catch (Exception e) {
			// TODO: handle exception
			  map.put("MENSAJE", "Error al crear cuenta");
			  System.out.println("ERROR: " + e.getMessage());
		      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	    	session.setAttribute("INFOPACIENT", datosPaciente.getId());
	    } else {
	        
	    	session.setAttribute("INFOPACIENT", "No se logueo como paciente");
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
