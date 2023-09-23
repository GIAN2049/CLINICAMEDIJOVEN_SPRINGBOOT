package org.proyecto.medijoven.repository;


import java.util.List;

import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u where u.username = ?1 and u.password=?2")
	public Usuario findByUsernameAndPassword(String user, String pass);

	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	public Usuario login(String user);
		
	@Query("SELECT m FROM Acceso a JOIN a.menu m where a.rol.id = ?1")
	public List<Menu> getMenusUser(int codRol);

	

}
