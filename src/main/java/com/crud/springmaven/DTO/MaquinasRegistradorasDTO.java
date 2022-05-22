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
@Table(name = "MaquinasRegistradoras")

public class MaquinasRegistradorasDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "piso")
	private String piso;
	
	@OneToMany
	@JoinColumn(name = "id")
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public MaquinasRegistradorasDTO(Long id, String piso) {
		super();
		this.id = id;
		this.piso = piso;
	}

	public MaquinasRegistradorasDTO() {
		super();
	}
	
}