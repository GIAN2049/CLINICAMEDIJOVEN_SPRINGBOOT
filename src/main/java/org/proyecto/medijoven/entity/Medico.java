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
@Table(name = "tb_medico")
@Getter
@Setter
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medico")
	private int id;
	private String nombre_medico;
	private String apellidos_medico;
	@Column(name = "Dni")
	private String dni;
	@Column(name = "Direccion")
	private String direccion;
	@Column(name = "Correo")
	private String correo;
	@Column(name = "Telefono")
	private String telefono;
	@Column(name = "Sexo")
	private String sexo;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "medico")
    private List<CitaMedica> citasMedicas;
	
	@JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<Receta> recetas;
	
	
}
