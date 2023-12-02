package org.proyecto.medijoven.repository;

import java.util.List;

import org.proyecto.medijoven.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicoRepository extends JpaRepository<Medico, Integer> {

	@Query("SELECT m FROM Medico m WHERE" + "( ?1 = -1 or m.id = ?1 ) and "
			+ "( ?2 = -1 or m.especialidad.id_especialidad = ?2)")
	public List<Medico> consultaMedico(int id, int id_especialidad);

	@Query("SELECT m from Medico m where m.nombre_medico=?1")
	public List<Medico> findByNombres(String nombres);

	@Query("SELECT m from Medico m where m.apellidos_medico=?1")
	public List<Medico> findByApellidos(String apellidos);

	@Query("SELECT m from Medico m where m.especialidad.id_especialidad=?1")
	public List<Medico> findMedicoByIdEspecialidad(int id);

	@Query("SELECT m from Medico m where m.nombre_medico = ?1 and m.apellidos_medico = ?2")
	public List<Medico> listaMedicoNombreApellido(String nombre, String apellido);

	@Query("SELECT m from Medico m where m.nombre_medico = ?1 and m.apellidos_medico = ?2 and m.id != ?3")
	public List<Medico> listaMedicoNombreApellidoIgualActualiza(String nombre, String apellido, int idMedico);
}
