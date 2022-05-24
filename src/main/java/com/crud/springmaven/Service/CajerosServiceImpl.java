package com.crud.springmaven.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springmaven.DAO.CajerosDAO;
import com.crud.springmaven.DTO.CajerosDTO;

@Service

public class CajerosServiceImpl implements CajerosService {

	@Autowired
	CajerosDAO cajerosDAO;

	@Override
	public List<CajerosDTO> listarCajeros() {
		return cajerosDAO.findAll();
	}

	@Override
	public CajerosDTO crearCajeros(CajerosDTO cajero) {
		return cajerosDAO.save(cajero);
	}

	@Override
	public CajerosDTO modificarCajeros(CajerosDTO cajero) {
		return cajerosDAO.save(cajero);
	}

	@Override
	public void eliminarCajero(Long id) {
	}

	@Override
	public CajerosDTO buscarCajero(Long id) {
		return cajerosDAO.findById(id).get();
	}

}
