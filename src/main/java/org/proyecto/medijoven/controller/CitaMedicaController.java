package org.proyecto.medijoven.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.CitaMedica;
import org.proyecto.medijoven.entity.CitaMedicaCancelada;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Receta;
import org.proyecto.medijoven.service.CitasCanceladasServiceImpl;
import org.proyecto.medijoven.service.CitasMedicasServiceImpl;
import org.proyecto.medijoven.service.MedicoServiceImpl;
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
public class CitaMedicaController {

	
	@Autowired
	private CitasMedicasServiceImpl citaMedicaService;
	@Autowired
	private CitasCanceladasServiceImpl citaMedicaCanceladasService;
	@Autowired
	private PacienteServiceImpl pacienteService;
	@Autowired
	private MedicoServiceImpl medicoService;
	
	@GetMapping("citas-medicas")
	public String listaCitaMedica(Model model) {
		List<CitaMedica> citasMedicas = citaMedicaService.listarTodos();
		List<Paciente> pacientes = pacienteService.listarTodos();
		List<Medico> medicos = medicoService.listarTodos();
		List<CitaMedicaCancelada> citasCanceladas = citaMedicaCanceladasService.listarTodos();
		
		model.addAttribute("citasMedicas", citasMedicas);
		model.addAttribute("citasMedicasCanceladas", citasCanceladas);
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("medicos", medicos);
		
		return "ListCitaMedica";
	}
	
	@GetMapping("cita-medica")
	public String viewFormCitaMedica(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		List<Medico> medicos = medicoService.listarTodos();
		
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("medicos", medicos);
		
		return "AddCitaMedica";
	}
	
	
	@PostMapping("/cita-medica/registrar")
	@ResponseBody
	public HashMap<String, String> registrarCitaMedica(CitaMedica obj) {
		HashMap<String, String> response = new HashMap<>();

		try {
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
	
	@DeleteMapping("/cita-medica/eliminar-y-registrar/{id}")
    @ResponseBody
    public HashMap<String, String> eliminarYRegistrarCitaCancelada(@PathVariable int id) {
        HashMap<String, String> map = new HashMap<>();

        try {
            // Obtener la cita médica que se eliminará
            CitaMedica citaMedica = citaMedicaService.obtenerCitaMedicaPorId(id);
            
            if (citaMedica != null) {
                // Eliminar la cita médica
                citaMedicaService.eliminarCitaMedica(id);
                
                // Crear una nueva cita cancelada con la información de la cita médica eliminada
                CitaMedicaCancelada citaCancelada = new CitaMedicaCancelada();
                citaCancelada.setPaciente(citaMedica.getPaciente());
                citaCancelada.setMedico(citaMedica.getMedico());
                citaCancelada.setFechaCita(citaMedica.getFechaCita());
                citaCancelada.setEstadoCita("Cancelada");
                citaCancelada.setObservaciones(citaMedica.getObservaciones());
                
                
                LocalDateTime fechaActual = LocalDateTime.now();

             // Convertir la fecha actual a un objeto Date
             //   Date fechaCancelacion = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                citaCancelada.setFechaCancelacion(fechaActual);

                citaMedicaCanceladasService.registrarCitaCancelada(citaCancelada);
                
                map.put("MENSAJE", "Cita médica eliminada y registrada como cita cancelada.");
            } else {
                map.put("MENSAJE", "No se encontró la cita médica.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("MENSAJE", "Error al eliminar y registrar la cita médica como cancelada: " + e.getMessage());
        }

        return map;
    }


	/*@DeleteMapping("/cita-medica/eliminar/{id}")
	@ResponseBody
	public HashMap<?, ?> eliminarCitaMedica(@PathVariable int id, CitaMedicaCancelada obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			citaMedicaService.eliminarCitaMedica(id);
			
			map.put("MENSAJE", "cita medica Eliminada");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MENSAJE", "Error al Eliminar cita medica");
		}
		return map;
	}	*/
}
