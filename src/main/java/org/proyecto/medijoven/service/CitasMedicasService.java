package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.CitaMedica;

public interface CitasMedicasService {
	
	public abstract List<CitaMedica> listarTodos();
	public abstract List<CitaMedica> listarCitasProgramadas();
	public abstract List<CitaMedica> listarCitasCanceladasPorId(int idPaciente);
	
	public abstract List<CitaMedica> obtenerCitasMedicasPorPaciente(int id);
	public abstract List<CitaMedica> obtenerCitasMedicasPorMedico(int id);
	public abstract CitaMedica obtenerCitaMedicaPorId(int id);
	public abstract CitaMedica guardarCitaMedica(CitaMedica citaMedica);
	public abstract void eliminarCitaMedica(int id);
}
