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

	@Override
	public List<Medico> consultaMedico(int idMedico, int idEspecialidad) {
		// TODO Auto-generated method stub
		return repo.consultaMedico(idMedico, idEspecialidad);
	}

	@Override
	public List<Medico> consultarMedicoPorEspecialidad(int id) {
		// TODO Auto-generated method stub
		return repo.findMedicoByIdEspecialidad(id);
	}

	@Override
	public List<Medico> buscarPorNombre(String nombres) {
		// TODO Auto-generated method stub
		return repo.findByNombres(nombres);
	}

	@Override
	public List<Medico> buscarPorApellido(String apellidos) {
		// TODO Auto-generated method stub
		return repo.findByApellidos(apellidos);
	}

	@Override
	public List<Medico> listaPorNombreApellidoIgual(String nombre, String apellido) {
		// TODO Auto-generated method stub
		return repo.listaMedicoNombreApellido(nombre, apellido);
	}

	@Override
	public List<Medico> validarNombreApellidosActualiza(String nombre, String apellido, int idMedico) {
		// TODO Auto-generated method stub
		return repo.listaMedicoNombreApellidoIgualActualiza(nombre, apellido, idMedico);
	}

}
