package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.VentaDTO;
import com.crud.springmaven.Service.VentaServiceImpl;

/**
 * Clase @VentaController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class VentaController {

	/** Se crea una instancia del tipo @VentaServiceImpl */
	@Autowired
	VentaServiceImpl ventaServiceImpl;
	
	/** Método para listar ventas */
	@GetMapping("/ventas")
	public List<VentaDTO> listarVentas() {
		return ventaServiceImpl.listarVentas();
	}
	
	/** Método para listar ventas por código */
	@GetMapping("/ventas/codigo/{codigo}")
	public List<VentaDTO> listarVentaCodigo(@PathVariable(name = "codigo") String codigo) {
		return null;
	}

	/** Método para crear una nueva venta */
	@PostMapping("/ventas")
	public VentaDTO crearVenta(@RequestBody VentaDTO venta) {
		return ventaServiceImpl.crearVenta(venta);
	}
	
	/** Método para buscar una venta por codigo */
	@GetMapping("/ventas/{id}")
	public VentaDTO buscarVentaId(@PathVariable(name = "codigo") Long codigo) {
		return ventaServiceImpl.buscarVenta(codigo);
	}
	
	/** Método para actualizar una venta */
	@PutMapping("/ventas/{id}")
	public VentaDTO actualizarVenta(@PathVariable(name = "codigo") Long codigo,
			@RequestBody VentaDTO venta) {
		
	/** Se definen instancias del tipo Ventas */
	VentaDTO venta_a_actualizar = new VentaDTO();
	VentaDTO actualizado = new VentaDTO();
		
	/** Se filtra la venta a actualizar por código */
	venta_a_actualizar = ventaServiceImpl.buscarVenta(codigo);
		
	/** Se actualizan los valores */
	venta_a_actualizar.setId(venta.getId());
	venta_a_actualizar.setCajero(venta.getCajero());
	venta_a_actualizar.setMaquina(venta.getMaquina());
	venta_a_actualizar.setProducto(venta.getProducto());

	actualizado = ventaServiceImpl.modificarVenta(venta_a_actualizar);

		return actualizado;
	}
}
