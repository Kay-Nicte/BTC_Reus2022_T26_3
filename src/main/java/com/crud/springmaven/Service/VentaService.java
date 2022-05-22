package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.VentaDTO;

public interface VentaService {
	
	//Listar las ventas
	public List<VentaDTO> listarVentas();
	
	//Crear venta
    public VentaDTO crearVenta(VentaDTO venta);

    //Modificar venta
    public VentaDTO modificarVenta(VentaDTO venta);

    //Eliminar venta
    public void eliminarVenta(Long codigo);
    
    //Buscar venta
    public VentaDTO buscarVenta(Long codigo);	

}
