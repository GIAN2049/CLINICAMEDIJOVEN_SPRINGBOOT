package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.HistoriaClinica;
import org.proyecto.medijoven.repository.IHistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService{
	
	@Autowired
	private IHistoriaClinicaRepository repo;
	

	@Override
	public HistoriaClinica obtenerHistoriaClinicaPorId(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public List<HistoriaClinica> obtenerHistoriasClinicasPorPaciente(int idPaciente) {
		// TODO Auto-generated method stub
		return repo.findByPaciente_Id(idPaciente);
	}

	@Override
	public HistoriaClinica guardarHistoriaClinica(HistoriaClinica historiaClinica) {
		// TODO Auto-generated method stub
		return repo.save(historiaClinica);
	}

	@Override
	public void eliminarHistoriaClinica(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public List<HistoriaClinica> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
