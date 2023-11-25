package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Horario;

public interface HorarioService {
	public abstract List<Horario> listarTodos();
	public abstract Horario guardar(Horario obj);
	public abstract void eliminar(int codigo);
	public abstract Horario buscarPorId(int cod);
}
