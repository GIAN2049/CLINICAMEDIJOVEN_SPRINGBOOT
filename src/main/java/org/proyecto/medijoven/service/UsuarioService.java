package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Usuario;

public interface UsuarioService {

	public abstract Usuario loginUsuario(String username);
	public abstract List<Menu> traerMenus(int rol);
	
}
