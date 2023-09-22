package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Farmaco;

public interface CategoriaService {
	

	public abstract List<Categoria> listarTodos();
	public abstract Categoria guardar(Categoria obj);
	public abstract void eliminar(int codigo);
	public abstract Categoria buscarPorId(int cod);
	public abstract List<Farmaco> buscarFarmacoPorIdCategoria(int cod);
	
}
