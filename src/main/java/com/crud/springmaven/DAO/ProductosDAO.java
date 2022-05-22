package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.ProductosDTO;

public interface ProductosDAO extends JpaRepository<ProductosDTO, Long>{

}