package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.service.RolServiceImpl;
import org.proyecto.medijoven.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/medijoven/dashboard")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired 
	private RolServiceImpl rolService;
	
	
	@GetMapping("/usuarios")
	public String ListUsuarios(Model model) {

		List<Usuario> usuarios = usuarioService.listarUsuarios();
		List<Rol> roles = rolService.listarRoles();
		
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("roles", roles);
		
		return "ListUsuarios";
	}
	
	@GetMapping("/usuario")
	public String viewFormUsuario(Model model) {
		List<Rol> roles = rolService.listarRoles();
		model.addAttribute("roles", roles);
		return "AddUsuario";
	}
	
	@PostMapping("/usuario/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarPaciente(Usuario obj){
		
		HashMap<String, String> map = new HashMap<String, String>();
		Usuario user = usuarioService.registrarUsuario(obj);
	 
		if(user == null) {
			map.put("MENSAJE", "Error al registrar Usuario");
		}else {
			map.put("MENSAJE", "Usuario Registrado");
		}
		
		return map;
		
	}
}
