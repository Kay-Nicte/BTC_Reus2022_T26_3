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

import com.crud.springmaven.DTO.CajerosDTO;
import com.crud.springmaven.Service.CajerosServiceImpl;


/**
 * Clase @CajerosController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class CajerosController {

	/** Se crea una instancia del tipo @AlmacenesServiceImpl */
	@Autowired
	CajerosServiceImpl cajerosServiceImpl;

	/** Método para listar cajeros */
	@GetMapping("/cajeros")
	public List<CajerosDTO> listarCajeros() {
		return cajerosServiceImpl.listarCajeros();
	}
	
	/** Método para listar cajeros por código */
	@GetMapping("/cajeros/codigo/{codigo}")
	public List<CajerosDTO> listarCajerosCodigo(@PathVariable(name = "codigo") String codigo) {
		return null;
	}

	/** Método para crear un nuevo cajero */
	@PostMapping("/cajeros")
	public CajerosDTO crearCajero(@RequestBody CajerosDTO cajero) {
		return cajerosServiceImpl.crearCajeros(cajero);
	}
	
	/** Método para buscar un cajero por codigo */
	@GetMapping("/cajeros/{id}")
	public CajerosDTO buscarCajeroId(@PathVariable(name = "codigo") Long codigo) {
		return cajerosServiceImpl.buscarCajero(codigo);
	}
	
	/** Método para actualizar un cajero */
	@PutMapping("/cajeros/{id}")
	public CajerosDTO actualizarCajero(@PathVariable(name = "codigo") Long codigo,
			@RequestBody CajerosDTO cajeros) {
		
	/** Se definen instancias del tipo Cajeros */
	CajerosDTO cajero_a_actualizar = new CajerosDTO();
	CajerosDTO actualizado = new CajerosDTO();
		
	/** Se filtra el cajero a actualizar por código */
	cajero_a_actualizar = cajerosServiceImpl.buscarCajero(codigo);
		
	/** Se actualizan los valores */
	cajero_a_actualizar.setId(cajeros.getId());
	cajero_a_actualizar.setNomApels(cajeros.getNomApels());

	actualizado = cajerosServiceImpl.modificarCajeros(cajero_a_actualizar);

		return actualizado;
	}
	
	/** Método para eliminar un cajero */
	@DeleteMapping("/cajeros/{id}")
	public void eliminarCajero(@PathVariable(name = "codigo") Long codigo) {
		cajerosServiceImpl.eliminarCajero(codigo);
		System.out.println("Cajero eliminado con exito.");
	}
}