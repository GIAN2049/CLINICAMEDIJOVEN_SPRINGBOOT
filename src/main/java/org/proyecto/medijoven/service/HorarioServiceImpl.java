package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Horario;
import org.proyecto.medijoven.repository.IHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioServiceImpl implements HorarioService{

		
	@Autowired
	private IHorarioRepository repo;

	@Override
	public List<Horario> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Horario guardar(Horario obj) {
		// TODO Auto-generated method stub
		obj.setEstado("Disponible");
		return repo.save(obj);
	}

	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Horario buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}

	@Override
	public List<Horario> obtenerHorarioPorMedico(int idMedico) {
		// TODO Auto-generated method stub
		return repo.getHorarByMedico(idMedico);
	}
	
}
