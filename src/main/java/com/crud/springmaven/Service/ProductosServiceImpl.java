package com.crud.springmaven.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springmaven.DAO.ProductosDAO;
import com.crud.springmaven.DTO.ProductosDTO;

@Service 

public class ProductosServiceImpl implements ProductosService{

	@Autowired
	ProductosDAO productosDAO;
	
	@Override
	public List<ProductosDTO> listarProductos() {
		return productosDAO.findAll();
	}

	@Override
	public ProductosDTO crearProductos(ProductosDTO producto) {
		return productosDAO.save(producto);
	}

	@Override
	public ProductosDTO modificarProductos(ProductosDTO producto) {
		return productosDAO.save(producto);

	}

	@Override
	public void eliminarProducto(Long codigo) {
	}

	@Override
	public ProductosDTO buscarProducto(Long codigo) {
		return productosDAO.findById(codigo).get();
	}

}