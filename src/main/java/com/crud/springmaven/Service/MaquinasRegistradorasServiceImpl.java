package com.crud.springmaven.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springmaven.DAO.MaquinasRegistradorasDAO;
import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;

@Service 

public class MaquinasRegistradorasServiceImpl implements MaquinasRegistradorasService{

	@Autowired
	MaquinasRegistradorasDAO maquinasRegistradorasDAO;

	@Override
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras() {
		return maquinasRegistradorasDAO.findAll();
	}

	@Override
	public MaquinasRegistradorasDTO crearMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora) {
		return maquinasRegistradorasDAO.save(maquinaRegistradora);
	}

	@Override
	public MaquinasRegistradorasDTO modificarMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora) {
		return maquinasRegistradorasDAO.save(maquinaRegistradora);
	}

	@Override
	public void eliminarMaquinaRegistradora(Long codigo) {		
	}

	@Override
	public MaquinasRegistradorasDTO buscarMaquinaRegistradora(Long codigo) {
		return maquinasRegistradorasDAO.findById(codigo).get();
	}
	
}
