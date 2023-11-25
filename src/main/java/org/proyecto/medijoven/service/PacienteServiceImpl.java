package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Paciente;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.proyecto.medijoven.repository.IpacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private IpacienteRepository repo;

	@Autowired
	private IUsuarioRepository repoUser;

	//@Autowired
	//private BCryptPasswordEncoder encoder;

	@Override
	public List<Paciente> listarTodos() {
		// TODO Auto-generated method stub

		return repo.findAll();
	}

	@Override
	public List<Paciente> UltimosPacientes() {
		// TODO Auto-generated method stub
		return repo.findTop5ByOrderByIdDesc();
	}

	@Override
	@Transactional
	public Paciente guardar(Paciente obj, String password) {
		// TODO Auto-generated method stub
		/*try {
			Usuario user = new Usuario();
			user.setUsername(obj.getDni());
			user.setPassword(encoder.encode(password));
			Rol rol = new Rol();
			rol.setIdRol(3);
			user.setRol(rol);
			
			obj.setId(obj.getId());
			
			user.setPaciente(obj);

			repoUser.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		return repo.save(obj);
	}
	
	@Override
	public Paciente actualizar(Paciente obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}
	
	@Override
	public void eliminar(int codigo) {
		// TODO Auto-generated method stub
		repo.deleteById(codigo);
	}

	@Override
	public Paciente buscarPorId(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElse(null);
	}

	

}
