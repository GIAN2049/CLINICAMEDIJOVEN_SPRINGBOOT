package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.CitaMedicaCancelada;

public interface CitasCanceladasService {
	public abstract List<CitaMedicaCancelada> listarTodos();
	public abstract CitaMedicaCancelada registrarCitaCancelada(CitaMedicaCancelada obj);
	public abstract void eliminarRegistrosDentrodeUnminuto();
	public abstract void eliminarRegistros(int cod);
}
