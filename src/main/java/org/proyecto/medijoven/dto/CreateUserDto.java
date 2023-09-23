package org.proyecto.medijoven.dto;

import org.proyecto.medijoven.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

	private String username;

	private String password;

	private String nombreCompleto;

	private String telefono;
	
	private Rol roles;
}
