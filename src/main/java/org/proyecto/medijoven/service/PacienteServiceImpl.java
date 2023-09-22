package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.repository.IpacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService{
	
	@Autowired
	private IpacienteRepository repo;
	
	@Override
	public List<Paciente> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public List<Paciente> UltimosPacientes() {
		// TODO Auto-generated method stub
		return repo.findTop5ByOrderByIdDesc();
	}

	@Override
	public Paciente guardar(Paciente obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Paciente buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}

	

}
