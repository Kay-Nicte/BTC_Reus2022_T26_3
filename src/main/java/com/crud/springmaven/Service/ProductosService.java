package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.ProductosDTO;

public interface ProductosService {
	
	//Listar los productos
	public List<ProductosDTO> listarProductos();
	
	//Crear producto
    public ProductosDTO crearProductos(ProductosDTO producto);

    //Modificar producto
    public ProductosDTO modificarProductos(ProductosDTO producto);

    //Eliminar producto
    public void eliminarProducto(Long codigo);
    
    //Buscar producto
    public ProductosDTO buscarProducto(Long codigo);

}