package org.proyecto.medijoven.service;

import java.util.List;
import java.util.Random;

import org.proyecto.medijoven.entity.Farmaceutico;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IFarmaceuticoRepository;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class FarmaceuticoServiceImpl implements FarmaceuticoService{

	@Autowired
	private IFarmaceuticoRepository repo;
	
	@Override
	public List<Farmaceutico> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Farmaceutico guardar(Farmaceutico obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Farmaceutico buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}
}
