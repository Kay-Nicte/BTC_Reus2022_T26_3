package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.CajerosDTO;

public interface CajerosService {

	//Listar los cajeros
	public List<CajerosDTO> listarCajeros();
	
	//Crear Cajero
    public CajerosDTO crearCajeros(CajerosDTO cajero);

    //Modificar Cajero
    public CajerosDTO modificarCajeros(CajerosDTO cajero);

    //Eliminar Cajero
    public void eliminarCajero(Long codigo);
    
    //Buscar Cajero
    public CajerosDTO buscarCajero(Long codigo);
	
}