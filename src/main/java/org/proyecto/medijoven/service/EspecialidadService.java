package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Especialidad;

public interface EspecialidadService {
	
	public abstract List<Especialidad> listarTodos();
	public abstract Especialidad guardar(Especialidad obj);
	public abstract void eliminar(int codigo);
	public abstract Especialidad buscarPorId(int cod);
	
}
