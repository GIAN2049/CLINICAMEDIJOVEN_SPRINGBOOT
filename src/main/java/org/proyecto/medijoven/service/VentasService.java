package org.proyecto.medijoven.service;

import java.util.List;

import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Ventas;

public interface VentasService {
	public abstract List<Ventas> listVentas();
	public abstract Ventas agregarProductoAlCarrito(Ventas venta);
	public abstract void eliminarProductoDelCarrito(String nombre);
	public abstract void actualizarStock(Farmaco farmaco, int cantidad);
	public abstract List<Ventas> listVentasCarrito();
	public abstract double calcularTotal();
	public abstract void registrarVentasDB();
}
