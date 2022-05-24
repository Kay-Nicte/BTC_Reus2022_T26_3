package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.ProductosDTO;
import com.crud.springmaven.Service.ProductosServiceImpl;

/**
 * Clase @ProductosController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")

public class ProductosController {
	
	/** Se crea una instancia del tipo @ProductosServiceImpl */
	@Autowired
	ProductosServiceImpl productosServiceImpl;

	/** Método para listar productos */
	@GetMapping("/productos")
	public List<ProductosDTO> listarProductos() {
		return productosServiceImpl.listarProductos();
	}

	/** Método para crear un nuevo producto */
	@PostMapping("/productos")
	public ProductosDTO crearProducto(@RequestBody ProductosDTO producto) {
		return productosServiceImpl.crearProductos(producto);
	}
	
	/** Método para buscar un producto por codigo */
	@GetMapping("/productos/{id}")
	public ProductosDTO buscarProductoId(@PathVariable(name = "id") Long id) {
		return productosServiceImpl.buscarProducto(id);
	}
	
	/** Método para actualizar un producto */
	@PutMapping("/productos/{id}")
	public ProductosDTO actualizarProducto(@PathVariable(name = "id") Long id,
			@RequestBody ProductosDTO productos) {
		
	/** Se definen instancias del tipo Productos */
	ProductosDTO producto_a_actualizar = new ProductosDTO();
	ProductosDTO actualizado = new ProductosDTO();
		
	/** Se filtra el producto a actualizar por código */
	producto_a_actualizar = productosServiceImpl.buscarProducto(id);
		
	/** Se actualizan los valores */
	producto_a_actualizar.setId(productos.getId());
	producto_a_actualizar.setNombre(productos.getNombre());
	producto_a_actualizar.setPrecio(productos.getPrecio());

	actualizado = productosServiceImpl.modificarProductos(producto_a_actualizar);

		return actualizado;
	}
	
	/** Método para eliminar un producto */
	@DeleteMapping("/productos/{id}")
	public void eliminarProducto(@PathVariable(name = "id") Long id) {
		productosServiceImpl.eliminarProducto(id);
		System.out.println("Producto eliminado con exito.");
	}
}