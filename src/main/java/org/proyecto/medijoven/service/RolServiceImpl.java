package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Rol;
import org.proyecto.medijoven.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private IRolRepository rolRepository;

	@Override
	public List<Rol> listarRoles() {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}
	
	
}
