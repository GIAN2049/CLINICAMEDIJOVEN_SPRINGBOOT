package org.proyecto.medijoven;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.proyecto.medijoven.entity.Medico;
import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.service.RolServiceImpl;
import org.proyecto.medijoven.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClinicamedijovenApplicationTests {

	
	@Autowired
	private RolServiceImpl rolService;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;

	
	@Test
	void contextLoads() {
		
		/*
		List<Rol> roles = rolService.listarRoles();
		
		for (Rol rol : roles) {
			System.out.println(rol.getIdRol() + "---" + rol.getNombre());
		}
		*/
		List<Medico> medicos = usuarioService.traerMedicosSinRrol();
		
		
		for (Medico medico : medicos) {
			System.out.println(medico.getNombre_medico());
		}
		
		
		
	}

}
