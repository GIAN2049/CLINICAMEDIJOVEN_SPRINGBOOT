package org.proyecto.medijoven.repository;


import java.util.List;

import org.proyecto.medijoven.entity.Farmaceutico;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u where u.username = ?1 and u.password=?2")
	public Usuario findByUsernameAndPassword(String user, String pass);

	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	public Usuario login(String user);
	
	@Query("SELECT u.paciente FROM Usuario u WHERE u.id = ?1")
    Paciente findPacienteByUserId(int idUsuario);
		
	@Query("SELECT m FROM Acceso a JOIN a.menu m where a.rol.id = ?1")
	public List<Menu> getMenusUser(int codRol);
	

	//@Query("SELECT m FROM Medico m LEFT JOIN Usuario u ON m.id = u.medico.id WHERE u.rol IS NULL")
	//@Query("SELECT m FROM Medico m LEFT JOIN Usuario u ON m.id = u.medico.id WHERE u.rol IS NULL")
	//@Query("SELECT m FROM Medico m LEFT JOIN m.usuario u WHERE u.rol IS NULL")
	//@Query("SELECT m FROM Medico m LEFT JOIN Usuario u ON m.id = u.medico.id WHERE u.rol IS NULL")
	@Query("SELECT m FROM Medico m LEFT JOIN m.usuario u WHERE u.rol IS NULL")
	public List<Medico> getMedicosSinRol();
	
	//List<Medico> findMedicosSinUsuario();

	@Query("SELECT f FROM Farmaceutico f LEFT JOIN f.usuario u WHERE u.rol IS NULL")
	public List<Farmaceutico> getFameceuticosSinRol();
	

} 
