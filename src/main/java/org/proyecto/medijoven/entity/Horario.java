package org.proyecto.medijoven.entity;


import java.time.LocalTime;
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
@Table(name = "tb_horario")
@Getter
@Setter
public class Horario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;
	
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

    @Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hora;
	
	private String estado;
}
