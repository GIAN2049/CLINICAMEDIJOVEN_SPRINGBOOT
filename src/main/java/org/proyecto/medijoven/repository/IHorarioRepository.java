package org.proyecto.medijoven.repository;

import java.util.List;

import org.proyecto.medijoven.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IHorarioRepository extends JpaRepository<Horario, Integer>{
	
	@Query("SELECT h FROM Horario h where h.medico.id = ?1")
	public List<Horario> getHorarByMedico(int idMedico);

}
