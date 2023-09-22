package org.proyecto.medijoven.service;

import java.util.List;
import java.util.Optional;
import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.repository.IFarmacosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmacoServiceImpl implements FarmacoService{
	
	@Autowired
	private IFarmacosRepository repo;

	@Override
	public List<Farmaco> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Farmaco guardar(Farmaco obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Farmaco buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}
	

}
