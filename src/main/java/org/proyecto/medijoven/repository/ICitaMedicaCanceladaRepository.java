package org.proyecto.medijoven.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.proyecto.medijoven.entity.CitaMedicaCancelada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitaMedicaCanceladaRepository extends JpaRepository<CitaMedicaCancelada, Integer>{
	List<CitaMedicaCancelada> findFirstByFechaCancelacionBeforeOrderByFechaCancelacion(LocalDateTime limiteTiempo);
}
