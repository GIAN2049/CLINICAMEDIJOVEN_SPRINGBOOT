package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Farmaco;

public interface FarmacoService {

	public abstract List<Farmaco> listarTodos();

	public abstract Farmaco guardar(Farmaco obj); 

	public abstract void eliminar(int codigo);

	public abstract Farmaco buscarPorId(int cod); 
}
