package org.proyecto.medijoven.repository;
import java.util.List;

import org.proyecto.medijoven.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {

	//OBTENER HISTORIA CLINICA DE POR EL ID DEL PACIENTE
	List<HistoriaClinica> findByPaciente_Id(int id_paciente);
}
