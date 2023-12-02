package org.proyecto.medijoven.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Ventas;
import org.proyecto.medijoven.repository.IVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentasServiceImpl implements VentasService{

	@Autowired
	private IVentasRepository repo;
	
	@Autowired
	private FarmacoService serviceFarmaco;
	
	private List<Ventas> carrito = new ArrayList<>();
	private Long id = 1L;
	
	@Override
	public List<Ventas> listVentas() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Ventas agregarProductoAlCarrito(Ventas venta) {
		// TODO Auto-generated method stub
		Farmaco farmaco = venta.getFarmaco();
		int cantidad = venta.getCantidad();
		double precioUnitario = farmaco.getPrecio();
		double precioTotal = precioUnitario * cantidad;
		
		venta.setPrecoUnitario(precioUnitario);
		venta.setPrecioTotal(precioTotal);
		
		
		carrito.add(venta);
		return venta;
	}

	@Override
	public void eliminarProductoDelCarrito(String nombre) {
		// TODO Auto-generated method stub
		carrito.removeIf(venta -> venta.getFarmaco().getNombre().equals(nombre));
		
	}

	@Override
	public void actualizarStock(Farmaco farmaco, int cantidad) {
		// TODO Auto-generated method stub
		int nuevoStock = farmaco.getStock() - cantidad;
		farmaco.setStock(nuevoStock);
		serviceFarmaco.guardar(farmaco);
	}

	@Override
	public List<Ventas> listVentasCarrito() {
		// TODO Auto-generated method stub
		return carrito;
	}

	@Override
	public double calcularTotal() {
		// TODO Auto-generated method stub
		double totalGeneral = 0;
		for (Ventas ventas : carrito) {
			totalGeneral += ventas.getPrecioTotal();
		}
		return totalGeneral;
	}

	@Override
	public void registrarVentasDB() {
		// TODO Auto-generated method stub
		for (Ventas ventas : carrito) {
			actualizarStock(ventas.getFarmaco(), ventas.getCantidad());
		}
		
		repo.saveAll(carrito);
		carrito.clear();
	}

}
