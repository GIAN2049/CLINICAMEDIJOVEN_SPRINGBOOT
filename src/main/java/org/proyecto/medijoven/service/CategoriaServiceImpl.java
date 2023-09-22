package org.proyecto.medijoven.service;

import java.util.List;
import java.util.Optional;

import org.proyecto.medijoven.entity.Categoria;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private ICategoriaRepository repo;

	@Override
	public List<Categoria> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Categoria guardar(Categoria obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Categoria buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}

	@Override
	public List<Farmaco> buscarFarmacoPorIdCategoria(int cod) {
		// TODO Auto-generated method stub
		return repo.findFarmacoByIdCategoria(cod);
	}
}
