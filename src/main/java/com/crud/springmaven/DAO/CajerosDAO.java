package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.CajerosDTO;

public interface CajerosDAO extends JpaRepository<CajerosDTO, Long>{

}
