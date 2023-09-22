package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Medico;

public interface MedicoService {
	
	public abstract List<Medico> listarTodos();

	public abstract Medico guardar(Medico obj); 

	public abstract void eliminar(int codigo);

	public abstract Medico buscarPorId(int cod);
}
