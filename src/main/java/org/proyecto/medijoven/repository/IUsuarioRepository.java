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
	
	/*SELECT m.id, m.nombre, m.icon, mi.nombre, mi.url
	FROM menu m join menu_item mi on m.id = mi.menu_id
	join acceso a on a.id_menu = m.id where a.id_rol = 1;*/
	
	//@Query("SELECT NEW org.proyecto.medijoven.entity.MenuItemDTO(m.id, m.nombre, m.icon, mi.nombre, mi.url) " +
	           //"FROM Menu m JOIN m.items mi JOIN m.accesos a WHERE a.rol.id = ?1")
	
	/*SELECT m.id, m.nombre, m.icon
	FROM menu m INNER JOIN acceso a ON m.id = a.id_menu
	WHERE a.id_rol = 1;*/
	
	@Query("SELECT m FROM Acceso a JOIN a.menu m where a.rol.id = ?1")
	public List<Menu> getMenusUser(int codRol);

	

}
