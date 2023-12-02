package org.proyecto.medijoven.controller;

import java.util.HashMap;
import java.util.List;

import org.proyecto.medijoven.entity.Farmaco;
import org.proyecto.medijoven.entity.Ventas;
import org.proyecto.medijoven.service.FarmacoService;
import org.proyecto.medijoven.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("medijoven/dashboard")
public class VentasController {
	
	@Autowired
	private VentasService ventaService;
	
	@Autowired
	private FarmacoService farmacoService;
	
	
	
	@GetMapping("/venta")
	public String viewFormVenta(Model model) {
		List<Farmaco> farmacos = farmacoService.listarTodos();
		List<Ventas> ventas = ventaService.listVentasCarrito();
		List<Ventas> detalleVenta = ventaService.listVentas();
		double totalGeneral = ventaService.calcularTotal();
		model.addAttribute("farmacos", farmacos);
		model.addAttribute("ventas", ventas);
		model.addAttribute("detalleVenta", detalleVenta);
		model.addAttribute("total", totalGeneral);
		
		return "AddVenta";
	}
	
	
	@GetMapping("/ventas")
	public String listVentas(Model model) {
		List<Farmaco> farmacos = farmacoService.listarTodos();
		List<Ventas> ventas = ventaService.listVentasCarrito();
		List<Ventas> detalleVenta = ventaService.listVentas();
		double totalGeneral = ventaService.calcularTotal();
		model.addAttribute("farmacos", farmacos);
		model.addAttribute("ventas", ventas);
		model.addAttribute("detalleVenta", detalleVenta);
		model.addAttribute("total", totalGeneral);
		
		return "ListVenta";
	}
	
	
	
	@PostMapping("/ventas/agregar")
	public String addFarmacos(Ventas venta) {
		ventaService.agregarProductoAlCarrito(venta);
		return "redirect:/medijoven/dashboard/venta";
		
	}
	
	@DeleteMapping("/ventas/eliminar/{nombre}")
	@ResponseBody
	public String eliminarProductoDelCarrito(@PathVariable String nombre) {		
		ventaService.eliminarProductoDelCarrito(nombre);
		return "redirect:/medijoven/dashboard/venta";
	}
	
	@PostMapping("/ventas/registrar")
	public String registrarVentas(RedirectAttributes redirect) {
		
		//HashMap<String, String> map = new HashMap<String, String>();
		
		ventaService.registrarVentasDB();
		
		redirect.addFlashAttribute("MENSAJE", "venta registrada");
		//map.put("MENSAJE", "Se registro nueva venta");
		
		return "redirect:/medijoven/dashboard/venta";
	}
}
