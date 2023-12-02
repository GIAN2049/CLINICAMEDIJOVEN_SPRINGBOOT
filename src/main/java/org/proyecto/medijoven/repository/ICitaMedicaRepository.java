package org.proyecto.medijoven.repository;
import java.util.List;

import org.proyecto.medijoven.entity.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICitaMedicaRepository extends JpaRepository<CitaMedica, Integer> {

	// Método para buscar citas médicas por ID de paciente
	List<CitaMedica> findByPaciente_Id(int idPaciente);
    
    // Método para buscar citas médicas por ID de médico
    List<CitaMedica> findByMedico_Id(int idMedico);
    
    @Query("SELECT c FROM CitaMedica c WHERE c.estadoCita = 'Programada'")
    List<CitaMedica> listCitasProgramadas();
    
    @Query("SELECT c FROM CitaMedica c WHERE c.estadoCita = 'Cancelada' AND c.paciente.id = ?1")
    List<CitaMedica> listarCitasCanceladasPorId(int idPaciente);
}

