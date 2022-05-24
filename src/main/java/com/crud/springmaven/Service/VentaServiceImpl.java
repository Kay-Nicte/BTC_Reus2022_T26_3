package com.crud.springmaven.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springmaven.DAO.VentaDAO;
import com.crud.springmaven.DTO.VentaDTO;

@Service

public class VentaServiceImpl implements VentaService{
	
	@Autowired
	VentaDAO ventaDAO;

	@Override
	public List<VentaDTO> listarVentas() {
		return ventaDAO.findAll();
	}

	@Override
	public VentaDTO crearVenta(VentaDTO venta) {
		return ventaDAO.save(venta);
	}

	@Override
	public VentaDTO modificarVenta(VentaDTO venta) {
		return ventaDAO.save(venta);
	}

	@Override
	public void eliminarVenta(Long id) {		
	}

	@Override
	public VentaDTO buscarVenta(Long id) {
		return ventaDAO.findById(id).get();
	}
	
}