package org.proyecto.medijoven.service;

import org.proyecto.medijoven.repository.IMedicoRepository;
import org.proyecto.medijoven.repository.IpacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosHomeDashboardServiceImpl implements DatosHomeDashboardService{

	@Autowired
	private IMedicoRepository repoMedico;
	@Autowired
	private IpacienteRepository repoPaciente;
	
	@Autowired
	
	@Override
	public Long obtenerCantidadMedicos() {
		// TODO Auto-generated method stub
		return repoMedico.count();
	}

	@Override
	public Long obtenerCantidadPacientes() {
		// TODO Auto-generated method stub
		return repoPaciente.count();
	}
}
