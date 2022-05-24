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
@Table(name = "venta")

public class VentaDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cajero")
	private Long cajero;
	@Column(name = "maquina")
	private Long maquina;
	@Column(name = "producto")
	private Long producto;
	
	@OneToMany
	@JoinColumn(name = "id")
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCajero() {
		return cajero;
	}
	
	public void setCajero(Long cajero) {
		this.cajero = cajero;
	}
	
	public Long getMaquina() {
		return maquina;
	}
	
	public void setMaquina(Long maquina) {
		this.maquina = maquina;
	}
	
	public Long getProducto() {
		return producto;
	}
	
	public void setProducto(Long producto) {
		this.producto = producto;
	}

}
