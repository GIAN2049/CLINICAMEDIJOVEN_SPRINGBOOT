package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Receta;

public interface RecetaService {
	public abstract List<Receta> listarTodos();
	public abstract Receta guardar(Receta obj);
	public abstract void eliminar(int codigo);
	public abstract Receta buscarPorId(int cod);
}
