package org.proyecto.medijoven.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.CitaMedica;
import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.entity.Horario;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.service.CitasMedicasService;
import org.proyecto.medijoven.service.EspecialidadService;
import org.proyecto.medijoven.service.HorarioService;
import org.proyecto.medijoven.service.MedicoService;
import org.proyecto.medijoven.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/medijoven/dashboard")
public class ReservarCitaController {

	
	@Autowired
	private CitasMedicasService citaMedicaService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private EspecialidadService especialidadService;
	@Autowired
	private HorarioService horarioService;
	
	@GetMapping("/mis-citas")
	public String listaCitaMedica(Model model, HttpSession session) {
		
		int idPaciente = (int) session.getAttribute("INFOPACIENT");
		List<CitaMedica> citasMedicas = citaMedicaService.obtenerCitasMedicasPorPaciente(idPaciente);
		List<CitaMedica> citasCanceladas = citaMedicaService.listarCitasCanceladasPorId(idPaciente);
		model.addAttribute("miscitas", citasMedicas);
		model.addAttribute("miscitascanceladas", citasCanceladas);
		
		System.out.println("el id del paciente es" + idPaciente);
		return "ListMisCitas";
	}
	
	@GetMapping("/reservar-cita")
	public String viewFormCitaMedica(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		List<Medico> medicos = medicoService.listarTodos();
		
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("medicos", medicos);
		
		List<Especialidad> especialidades = especialidadService.listarTodos();
		model.addAttribute("especialidades", especialidades);
		
		return "AddReservaCita";
	}
	

	
	@PostMapping("/reservar-cita/guardar")
	@ResponseBody
	public HashMap<String, String> registrarCitaMedica(CitaMedica obj, @RequestParam int idMedico,
            														   @RequestParam int idEspecialidad,
            														   @RequestParam String fecha,
            														   @RequestParam String hora,
            														   @RequestParam String estado,
            														   HttpSession session) {
		
		HashMap<String, String> response = new HashMap<>();
		
		int idPaciente = (int) session.getAttribute("INFOPACIENT");
		
		try {
			
			Paciente paciente = new Paciente();
			paciente.setId(idPaciente);
			
			Medico medico = new Medico();
			medico.setId(idMedico);
			
			Especialidad especialidad = new Especialidad();
			especialidad.setId_especialidad(idEspecialidad);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			obj.setPaciente(paciente);
			obj.setMedico(medico);
			obj.setEspecialidad(especialidad);
			obj.setFechaCita(sdf.parse(fecha));
			
			obj.setHora(hora);
			
			CitaMedica nuevaCita = citaMedicaService.guardarCitaMedica(obj);
			if (nuevaCita != null) {
				response.put("MENSAJE", "Se registró una nueva cita médica con ID " + nuevaCita.getIdCitaMedica());
			} else {
				response.put("MENSAJE", "Error al registrar cita médica");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			response.put("MENSAJE", "Error al registrar cita médica: " + e.getMessage());
		}

		return response;
	}
	
	@ResponseBody
	@GetMapping("/consulta-medico")
	public List<Medico> consultaMedico(int idMedico, int idEspecialidad) {
		return medicoService.consultaMedico(idMedico, idEspecialidad);
	}

	@ResponseBody
	@GetMapping("/consulta-horario/{id}")
	public List<Horario> traerHorarioPorMedico(@PathVariable int id){
		return horarioService.obtenerHorarioPorMedico(id);
	}
	
	
	
	@PostMapping("/mis-citas/cancelar/{id}")
	@ResponseBody
	public HashMap<String, String> cancelarCitaMedica(@PathVariable int id) {
	    HashMap<String, String> map = new HashMap<>();
	    CitaMedica citaMedica = citaMedicaService.obtenerCitaMedicaPorId(id);
	    if (citaMedica != null) {
	        citaMedica.setEstadoCita("Cancelada");
	        citaMedicaService.guardarCitaMedica(citaMedica);
	        map.put("MENSAJE", "Se canceló la cita médica con éxito.");
	    } else {
	        map.put("MENSAJE", "Error al cancelar la cita médica.");
	    }
	    return map;
	}
	
	

	@GetMapping("/buscar/cita-medica/{id}")
	@ResponseBody
	public CitaMedica buscar(@PathVariable int id) {
		CitaMedica objCita = citaMedicaService.obtenerCitaMedicaPorId(id);
		return objCita;
	}

	@GetMapping("/buscar/cita-medica/paciente/{id}")
	@ResponseBody
	public List<CitaMedica> buscarCitaMedicaPorPaciente(@PathVariable int id) {
		List<CitaMedica> objCita = citaMedicaService.obtenerCitasMedicasPorPaciente(id);
		return objCita;
	}

	@PutMapping("/cita-medica/actualizar/{id}")
	@ResponseBody
	public HashMap<?, ?> actualizarCitaMedica(@PathVariable int id, CitaMedica obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		CitaMedica objCita = citaMedicaService.obtenerCitaMedicaPorId(id);
		if (objCita != null) {
			citaMedicaService.guardarCitaMedica(objCita);;
			map.put("MENSAJE", "Se actualizo cita medica");
		} else {
			map.put("MENSAJE", "Error al actualizar cita medica");
		}

		return map;
	}
	
	
	
	
}
