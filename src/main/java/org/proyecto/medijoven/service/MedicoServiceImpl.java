package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.repository.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService{
	
	@Autowired
	private IMedicoRepository repo;
	
	@Override
	public List<Medico> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Medico guardar(Medico obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		repo.deleteById(codigo);
		
	}

	@Override
	public Medico buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).get();
	}

}
