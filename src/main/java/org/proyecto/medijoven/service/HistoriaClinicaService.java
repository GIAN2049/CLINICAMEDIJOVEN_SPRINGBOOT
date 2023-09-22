package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.HistoriaClinica;

public interface HistoriaClinicaService {
	
	public abstract List<HistoriaClinica> listarTodos();
	public abstract HistoriaClinica obtenerHistoriaClinicaPorId(int id);
	public abstract List<HistoriaClinica> obtenerHistoriasClinicasPorPaciente(int idPaciente);
	public abstract HistoriaClinica guardarHistoriaClinica(HistoriaClinica historiaClinica);
	public abstract void eliminarHistoriaClinica(int id);
	
}
