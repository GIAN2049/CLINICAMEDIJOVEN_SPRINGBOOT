package org.proyecto.medijoven.security;

import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class Autenticacion extends DaoAuthenticationProvider{

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	/*	final Usuario user = usuarioRepository.login(authentication.getName());
		if((user == null)) {
			throw new BadCredentialsException("Usuario o Contraseña Incorrectos");
		}
		
		//logica para autenticar al usuario
		
		final Authentication result = super.authenticate(authentication);
		return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
	*/
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		//return authentication.equals(UsernamePasswordAuthenticationToken.class);
		
		return false;
	}
	
	
	
	
	
}
