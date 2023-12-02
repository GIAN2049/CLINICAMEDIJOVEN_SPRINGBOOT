package org.proyecto.medijoven.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_venta")
@Getter
@Setter
public class Ventas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Long id;
	private int cantidad;
	@Column(name = "precio_unitario")
	private double precoUnitario;
	@Column(name = "precio_total")
	private double precioTotal;
	
	@ManyToOne
	@JoinColumn(name = "id_farmaco")
	private Farmaco farmaco;	
	
}
