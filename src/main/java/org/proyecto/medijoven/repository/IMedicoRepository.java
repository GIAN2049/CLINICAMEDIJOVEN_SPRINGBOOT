package org.proyecto.medijoven.repository;

import java.util.List;
import org.proyecto.medijoven.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicoRepository extends JpaRepository<Medico, Integer>{
	
	
	 @Query("SELECT m FROM Medico m WHERE"
				+ "( ?1 = -1 or m.id = ?1 ) and "
				+ "( ?2 = -1 or m.especialidad.id_especialidad = ?2)")
		public List<Medico> consultaMedico(int id, int id_especialidad);
}
