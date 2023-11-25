package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Farmaceutico;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Menu;
import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario loginUsuario(String username) {
		return usuarioRepository.login(username);
	}
	
	public List<Menu> traerMenus(int rol){
		return usuarioRepository.getMenusUser(rol);
	}

	@Override
	public Usuario registrarUsuario(Usuario obj) {
		// TODO Auto-generated method stub
		//PARA ENCRIPTAR LA CONTRASEÑA
		//obj.setPassword(passwordEncoder.encode(obj.getPassword()));	
		return usuarioRepository.save(obj);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public void eliminarUsuario(int id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public Usuario buscarUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<Medico> traerMedicosSinRrol() {
		// TODO Auto-generated method stub
		return usuarioRepository.getMedicosSinRol();
	}

	@Override
	public List<Farmaceutico> traerFarmeceuticoSinRol() {
		// TODO Auto-generated method stub
		return usuarioRepository.getFameceuticosSinRol();
	}

	@Override
	public Paciente traerPacientePorUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findPacienteByUserId(idUsuario);
	}

}
