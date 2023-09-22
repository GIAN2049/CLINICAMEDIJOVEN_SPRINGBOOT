package org.proyecto.medijoven.security;

import java.util.HashSet;
import java.util.Set;

import org.proyecto.medijoven.entity.Usuario;
import org.proyecto.medijoven.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails obj = null;

		Usuario user = repo.login(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("No se Encontro el Usuario: " + username);
		}
	
		
		Set<GrantedAuthority> rol = new HashSet<GrantedAuthority>();
		rol.add(new SimpleGrantedAuthority(user.getRol().getRol()));
		obj = new User(username, user.getPassword(), rol);
		

		return obj;
	}

}
