package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.proyecto.medijoven.service.CategoriaService;
import org.proyecto.medijoven.service.EspecialidadServiceImpl;
import org.proyecto.medijoven.service.FarmacoServiceImpl;
import org.proyecto.medijoven.service.MedicoServiceImpl;
import org.proyecto.medijoven.service.PacienteServiceImpl;
import org.proyecto.medijoven.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/medijoven/dashboard")
public class PacienteController {
	
	
	@Autowired
	private PacienteServiceImpl service;
		
	
	@GetMapping("/pacientes")
	public String ListaMedico(Model model) {
		List<Paciente> pacientes = service.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "ListPacientes";
	}
	
	@GetMapping("/paciente")
	public String viewFormMedico() {
		return "AddPaciente";
	}
	
	@PostMapping("/paciente/registrar")
	@ResponseBody
	public HashMap<?, ?> registrarPaciente(Paciente obj, @RequestParam("password") String password){
		HashMap<String, String> map = new HashMap<String, String>();
		Paciente objPaciente = service.guardar(obj);
		
		if(objPaciente != null) {
			map.put("MENSAJE", "Se registro nuevo paciente");
		}else {
			map.put("MENSAJE", "Error al registrar paciente");
		}
		return map;
	}
	
	
	@GetMapping("/buscar/paciente/{id}")
	@ResponseBody
	public Paciente buscar(@PathVariable int id) {
		Paciente objPaciente = service.buscarPorId(id);		
		return objPaciente;
	}
	
	@PutMapping("/paciente/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarPaciente(@PathVariable int id, Paciente obj){
		HashMap<String, String> map = new HashMap<String, String>();
		Paciente objPaciente = service.buscarPorId(id);
		if(objPaciente!=null) {
			service.actualizar(obj);
			map.put("MENSAJE", "Se actualizo paciente");
		}else {
			map.put("MENSAJE", "Error al actualizar paciente");
		}
		
		return map;
	}
	
	@DeleteMapping("/paciente/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarPaciente(@PathVariable int id){
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			service.eliminar(id);
			map.put("MENSAJE", "paciente Eliminado");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar paciente");
		}
		return map;
	}
	

	
}
