package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Paciente;

public interface PacienteService {
	public abstract List<Paciente> listarTodos();
	public abstract Paciente guardar(Paciente obj);
	public abstract Paciente actualizar(Paciente obj); 
	public abstract void eliminar(int codigo);
	public abstract Paciente buscarPorId(int cod);
	public abstract List<Paciente> UltimosPacientes();
}
