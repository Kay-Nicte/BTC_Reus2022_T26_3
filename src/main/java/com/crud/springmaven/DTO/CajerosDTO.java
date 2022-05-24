package com.crud.springmaven.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cajeros")

public class CajerosDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_apels")
	private String nomApels;

	
	@OneToMany
	@JoinColumn(name = "id")
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomApels() {
		return nomApels;
	}
	
	public void setNomApels(String nomApels) {
		this.nomApels = nomApels;
	}

	public CajerosDTO() {
		super();
	}

	public CajerosDTO(Long id, String nomApels) {
		super();
		this.id = id;
		this.nomApels = nomApels;
	}
		
}