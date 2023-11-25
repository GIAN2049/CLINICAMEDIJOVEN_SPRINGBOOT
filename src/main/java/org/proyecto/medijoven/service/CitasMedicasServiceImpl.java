package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.CitaMedica;
import org.proyecto.medijoven.repository.ICitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitasMedicasServiceImpl implements CitasMedicasService{
	
	@Autowired
	private ICitaMedicaRepository repo;
	
	
	@Override
	public List<CitaMedica> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<CitaMedica> obtenerCitasMedicasPorPaciente(int id) {
		// TODO Auto-generated method stub
		return repo.findByPaciente_Id(id);
	}
	
	@Override
	public List<CitaMedica> obtenerCitasMedicasPorMedico(int id) {
		// TODO Auto-generated method stub
		return repo.findByMedico_Id(id);
	}
	
	@Override
	public CitaMedica obtenerCitaMedicaPorId(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}
	
	@Override
	public CitaMedica guardarCitaMedica(CitaMedica citaMedica) {
		// TODO Auto-generated method stub
		citaMedica.setEstadoCita("Programada");
		
		return repo.save(citaMedica);
	}

	@Override
	public void eliminarCitaMedica(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	

}
