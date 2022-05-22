package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.VentaDTO;

public interface VentaDAO extends JpaRepository<VentaDTO, Long>{

}