package org.proyecto.medijoven.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_especialidad")
@Getter
@Setter
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_especialidad;
	@Column(name = "nombre_especialidad")
	private String nombre;
	@Column(name = "Descripcion")
	private String descripcion;

	@JsonIgnore
	@OneToMany(mappedBy = "especialidad")
	private List<Receta> recetas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "especialidad")
    private List<Medico> medicos;
}
