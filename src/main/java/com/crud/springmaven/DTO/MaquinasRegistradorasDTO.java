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
@Table(name = "maquinas_registradoras")

public class MaquinasRegistradorasDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "piso")
	private int piso;
	
	@OneToMany
	@JoinColumn(name = "id")
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public MaquinasRegistradorasDTO(Long id, int piso) {
		super();
		this.id = id;
		this.piso = piso;
	}

	public MaquinasRegistradorasDTO() {
		super();
	}
	
}