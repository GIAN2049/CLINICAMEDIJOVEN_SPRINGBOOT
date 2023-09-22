package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Especialidad;
import org.proyecto.medijoven.repository.IEspecialidadRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{

	@Autowired
	private IEspecialidadRepostory repo;
	
	@Override
	public List<Especialidad> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Especialidad guardar(Especialidad obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Especialidad buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).get();
	}

}
