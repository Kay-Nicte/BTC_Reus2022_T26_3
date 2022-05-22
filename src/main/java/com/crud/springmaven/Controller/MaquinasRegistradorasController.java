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

import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;
import com.crud.springmaven.Service.MaquinasRegistradorasServiceImpl;

/**
 * Clase @MaquinasRegistradorasController. Mappea las funcionalidades con las
 * rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class MaquinasRegistradorasController {

	/** Se crea una instancia del tipo @MaquinasRegistradorasServiceImpl */
	@Autowired
	MaquinasRegistradorasServiceImpl maquinasRegistradorasServiceImpl;

	/** Método para listar máquinas registradoras */
	@GetMapping("/maquinasregistradoras")
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras() {
		return maquinasRegistradorasServiceImpl.listarMaquinasRegistradoras();
	}

	/** Método para listar máquinas registradoras por código */
	@GetMapping("/maquinasregistradoras/codigo/{codigo}")
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras(@PathVariable(name = "codigo") String codigo) {
		return null;
	}

	/** Método para crear una nueva máquina registradora */
	@PostMapping("/maquinaregistradora")
	public MaquinasRegistradorasDTO crearMaquinaRegistradora(@RequestBody MaquinasRegistradorasDTO maquinaRegistradora) {
		return maquinasRegistradorasServiceImpl.crearMaquinaRegistradora(maquinaRegistradora);
	}

	/** Método para buscar una máquina registradora por codigo */
	@GetMapping("/maquinasregistradoras/{id}")
	public MaquinasRegistradorasDTO buscarMaquinaRegistradoraId(@PathVariable(name = "codigo") Long codigo) {
		return maquinasRegistradorasServiceImpl.buscarMaquinaRegistradora(codigo);
	}

	/** Método para actualizar una máquina registradora */
	@PutMapping("/maquinasregistradoras/{id}")

	public MaquinasRegistradorasDTO actualizarMaquinaRegistradora(@PathVariable(name = "codigo") Long codigo,
			@RequestBody MaquinasRegistradorasDTO maquinaRegistradora) {

	/** Se definen instancias del tipo maquinasRegistradoras */
	MaquinasRegistradorasDTO maquinaRegistradora_a_actualizar = new MaquinasRegistradorasDTO();
	MaquinasRegistradorasDTO actualizado = new MaquinasRegistradorasDTO();

	/** Se filtra la máquina registradora a actualizar por código */
	maquinaRegistradora_a_actualizar = maquinasRegistradorasServiceImpl.buscarMaquinaRegistradora(codigo);
	
	/** Se actualizan los valores */
	maquinaRegistradora_a_actualizar.setId(maquinaRegistradora.getId());
	maquinaRegistradora_a_actualizar.setPiso(maquinaRegistradora.getPiso());

	actualizado = maquinasRegistradorasServiceImpl.modificarMaquinaRegistradora(maquinaRegistradora_a_actualizar);

		return actualizado;
	}
		
	/** Método para eliminar una caja registradora */
	@DeleteMapping("/maquinaregistradora/{id}")
	public void eliminarMaquinaRegistradora(@PathVariable(name = "codigo") Long codigo) {
		maquinasRegistradorasServiceImpl.eliminarMaquinaRegistradora(codigo);
		System.out.println("Máquina registradora eliminada con exito.");
	}
}