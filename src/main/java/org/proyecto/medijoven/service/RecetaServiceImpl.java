package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Receta;
import org.proyecto.medijoven.repository.IRecetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaServiceImpl implements RecetaService{
	
	
	@Autowired
	private IRecetasRepository repo;
	
	@Override
	public List<Receta> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Receta guardar(Receta obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Receta buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}

}
