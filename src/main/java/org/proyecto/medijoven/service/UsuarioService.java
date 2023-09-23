package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;

public interface UsuarioService {

	public abstract Usuario loginUsuario(String username);
	public abstract Usuario registrarUsuario(Usuario obj);
	public abstract List<Menu> traerMenus(int rol);
	public abstract List<Usuario> listarUsuarios();
	public abstract void eliminarUsuario(int id);
	public abstract Usuario buscarUsuarioPorId(int id);
}
