package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Farmaceutico;



public interface FarmaceuticoService {
	public abstract List<Farmaceutico> listarTodos();
	public abstract Farmaceutico guardar(Farmaceutico obj);
	public abstract void eliminar(int codigo);
	public abstract Farmaceutico buscarPorId(int cod);
}
