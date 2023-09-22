package org.proyecto.medijoven.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.proyecto.medijoven.entity.CitaMedicaCancelada;
import org.proyecto.medijoven.repository.ICitaMedicaCanceladaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CitasCanceladasServiceImpl implements CitasCanceladasService {

	@Autowired
	private ICitaMedicaCanceladaRepository repo;

	@Override
	public List<CitaMedicaCancelada> listarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public CitaMedicaCancelada registrarCitaCancelada(CitaMedicaCancelada obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	@Scheduled(cron = "0 0/1 * * * ?")
	public void eliminarRegistrosDentrodeUnminuto() {

		LocalDateTime limiteTiempo = LocalDateTime.now().minusMinutes(1);

		List<CitaMedicaCancelada> registrosAntiguos = repo
				.findFirstByFechaCancelacionBeforeOrderByFechaCancelacion(limiteTiempo);

		if (!registrosAntiguos.isEmpty()) {
			repo.deleteAll(registrosAntiguos);
			System.out.println("Registros antiguos eliminados: " + registrosAntiguos.size());
		} else {
			//System.out.println("No se encontraron registros antiguos para eliminar.");
		}
	}

	@Override
	public void eliminarRegistros(int cod) {
		// TODO Auto-generated method stub
		repo.deleteById(cod);
	}

}
