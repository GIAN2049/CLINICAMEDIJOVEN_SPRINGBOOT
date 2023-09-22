package org.proyecto.medijoven.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_farmacos")
@Getter
@Setter
public class Farmaco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_farmacos;
	private String nombre;
	private int stock;
	private double precio;
	
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
}
