package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	
	public Usuario loginUsuario(String username) {
		return usuarioRepository.login(username);
	}
	
	public List<Menu> traerMenus(int rol){
		return usuarioRepository.getMenusUser(rol);
	}
	
}
