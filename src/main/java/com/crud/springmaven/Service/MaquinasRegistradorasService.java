package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;

public interface MaquinasRegistradorasService {

	//Listar las máquinas registradoras
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras();
		
	//Crear máquina registradora
	public MaquinasRegistradorasDTO crearMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora);
		
	//Modificar máquina registradora
	public MaquinasRegistradorasDTO modificarMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora);

	//Eliminar máquina registradora
	public void eliminarMaquinaRegistradora(Long id);
	    
	//Buscar máquina registradora
	public MaquinasRegistradorasDTO buscarMaquinaRegistradora(Long id);
	    
}