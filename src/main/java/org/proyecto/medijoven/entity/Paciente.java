package org.proyecto.medijoven.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pacientes")
@Getter
@Setter
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private int id;
	@Column(name = "nombre_paciente")
	private String nombre_paciente;
	@Column(name = "Apellidos")
	private String apellidos;
	@Column(name = "Dni")
	private String dni;
	@Column(name = "Direccion")
	private String direccion;
	@Column(name = "Telefono")
	private String telefono;
	@Column(name = "Sexo")
	private String sexo;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_nacimiento")
	private Date fecha_nacimiento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Receta> recetas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<HistoriaClinica> historiasClinicas;

	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<CitaMedica> citasMedicas;

	@OneToOne(mappedBy="paciente") 
	private Usuario usuario;
}
