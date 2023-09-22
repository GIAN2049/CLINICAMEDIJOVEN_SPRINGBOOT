package org.proyecto.medijoven.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_recetas")
@Getter
@Setter
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_receta")
	private int idReceta;
	
	@Column(name = "Diagnostico")
	private String diagnostico;
	
	@ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria") // Nueva columna para la relación con Farmaco
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_farmaco")
    private Farmaco farmaco;

    @Column(name = "fecha_receta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaReceta;
}
