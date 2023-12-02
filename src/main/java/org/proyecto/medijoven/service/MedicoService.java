package org.proyecto.medijoven.service;

import java.util.List;
import org.proyecto.medijoven.entity.Medico;

public interface MedicoService {
	
	public abstract List<Medico> listarTodos();

	public abstract Medico guardar(Medico obj); 

	public abstract void eliminar(int codigo);

	public abstract Medico buscarPorId(int cod);
	
	public abstract List<Medico> consultaMedico(int idMedico, int idEspecialidad);
	
	public abstract List<Medico> consultarMedicoPorEspecialidad(int id);
	
	public abstract List<Medico> buscarPorNombre(String nombres);
	
	public abstract List<Medico> buscarPorApellido(String apellidos);
	
	public abstract List<Medico> listaPorNombreApellidoIgual(String nombre, String apellido);
	
	public abstract List<Medico> validarNombreApellidosActualiza(String nombre, String apellido, int idMedico);
}
