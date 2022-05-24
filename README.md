<table>
<tr>
<td width="100px"><img src="https://github.com/OctavioBernalGH/BTC_Reus2022_UD16/blob/main/dou_logo.png" alt="Team DOU"/></td>
<td width="1000px"> <h2> Spring + JPA + H2 + Maven Ejercicio 1 Unidad 26 </h2> </td>

</tr>
</table>

[![Java](https://img.shields.io/badge/Java-FrontEnd-informational)]()
[![GitHub](https://img.shields.io/badge/GitHub-Repository-lightgrey)]()
[![SQL](https://img.shields.io/badge/SQL-DataBase-yellowgreen)]()
[![Spring](https://img.shields.io/badge/Spring-infrastructure-brightgreen)]()
[![Maven](https://img.shields.io/badge/Maven-ProjectStructure-blueviolet)]()

Este ejercicio ha sido realizado por los miembros del equipo 1. Dicho equipo esta formado por:

[- Ixabel Justo Etxeberria](https://github.com/Kay-Nicte)<br>
[- J.Oriol López Bosch](https://github.com/mednologic)<br>
[- Octavio Bernal](https://github.com/OctavioBernalGH)<br>
[- David Dalmau](https://github.com/DavidDalmauDieguez)

<p align="justify">Se crea un proyecto Maven utilizando la tecnología spring, se definen como componentes los spring services, la base de datos H2 y JPA. Se crea la estructura de proyecto en capas definiendo los paquetes de controllers, dao, dto y services. Para proseguir se crean las entidades 'ventas', 'productos', 'cajeros' y 'maquinasRegistradoras' con una relación de uno a muchos (one to many). Se definen las columnas y mediante anotaciones se mapea con los atributos de la entidad.</p>

A continuación se expondrá el desarrollo completo de la aplicación. A la hora de comenzar con un proyecto Spring es muy importante configurar el fichero application.propierties ubicado en la carpeta de resources. En este caso la configuración queda tal que así:

<p align="center">
  <img src="https://user-images.githubusercontent.com/103035621/170105803-c0b70ab5-3561-477f-afc6-8527f64b6881.png">
</p>

El siguiente paso corresponde a la definición de la base de datos según el esquema entidad relación, para ello se ha creado un script de creación de base de datos con varias tuplas de datos de prueba. El fichero es denominado data.sql y está ubicado en la misma carpeta que el application.propierties. A continuación se puede observar el código generado:

```sql

drop database if exists UD26_Ejercicio_3;
create database if not exists UD26_Ejercicio_3;

use UD26_Ejercicio_3;

/*Crear la tabla de Cajeros*/
CREATE TABLE cajeros(
id int auto_increment PRIMARY KEY,
nom_apels varchar(255)
);

/*Crear la tabla de Productos*/
CREATE TABLE productos(
id int auto_increment PRIMARY KEY,
nombre varchar(100),
precio int
);

/*Crear la tabla de Maquinas_Registradoras*/
CREATE TABLE maquinas_registradoras(
id int auto_increment PRIMARY KEY,
piso int
);

/*Crear la tabla de Venta*/
CREATE TABLE venta(
id int auto_increment primary key,
cajero int,
maquina int,
producto int,
FOREIGN KEY (cajero) REFERENCES cajeros(id),
FOREIGN KEY (maquina) REFERENCES maquinas_registradoras(id),
FOREIGN KEY (producto) REFERENCES productos(id)
);

INSERT INTO cajeros(nom_apels) VALUES ('Octavio Bernal');
INSERT INTO cajeros(nom_apels) VALUES ('David Dalmau');
INSERT INTO cajeros(nom_apels) VALUES ('Josep Oriol López');
INSERT INTO cajeros(nom_apels) VALUES ('Ixabel Justo');

/*Insert de Productos*/
INSERT INTO productos(nombre, precio) VALUES ('Cuaderno', 3);
INSERT INTO productos(nombre, precio) VALUES ('Borragoma', 1);
INSERT INTO productos(nombre, precio) VALUES ('Fosforito', 2);
INSERT INTO productos(nombre, precio) VALUES ('Agenda', 8);

/*Insert de Maquinas_Registradoras*/
INSERT INTO maquinas_registradoras(piso) VALUES (1);
INSERT INTO maquinas_registradoras(piso) VALUES (2);
INSERT INTO maquinas_registradoras(piso) VALUES (3);
INSERT INTO maquinas_registradoras(piso) VALUES (4);

/*Insert de Venta*/
INSERT INTO venta(cajero, maquina, producto) VALUES (1, 2, 3);
INSERT INTO venta(cajero, maquina, producto) VALUES (2, 3, 1);
INSERT INTO venta(cajero, maquina, producto) VALUES (3, 1, 4);
INSERT INTO venta(cajero, maquina, producto) VALUES (4, 4, 2);

```

El siguiente paso será crear las diferentes clases, cada clase representa una tabla y los atributos de cada clase representan las columnas de la base de datos, de esta manera mediante las diferentes anotaciones creamos un mapeo entre objeto-tabla.

Las principales anotaciones a la hora de mapear una clase con la tabla son:

@Entity --> Se va a crear una entidad.<br>
@Table(name = "nombre de la tabla") --> El nombre de la tabla corresponde a...<br>
@Id --> El siguiente atributo será la clave primaria.<br>
@GeneratedValue(strategy = GeneratedType.IDENTITY) --> La clave primaria se generará de forma auto incremental.<br>
@Column(name = "nombre_col") --> El nombre de la columna corresponde al atributo de abajo.<br>
private TipoVariable nombreCol;

A continuación se dejan cuatro desplegables con el código generado en las entidades:

<details>
  
<summary>Código generado en la entidad Cajas</summary>
  
<br>
  
```java
  
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
  
```
  
</details>


<details>
  
<summary>Código generado en la entidad Productos</summary>
  
<br>
  
```java

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
@Table(name = "productos")

public class ProductosDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;
	@Column(name = "precio")
	private int precio;
	
	@OneToMany
	@JoinColumn(name = "id")
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public ProductosDTO(Long id, String nombre, int precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public ProductosDTO() {
		super();
	}
	
}

  
```
  
</details>


<details>
  
<summary>Código generado en la entidad Ventas</summary>
  
<br>
  
```java
  
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

  
```
  
</details>


<details>
  
<summary>Código generado en la entidad Maquinas Registradoras</summary>
  
<br>
  
```java
  
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
  
```
  
</details>

Se proseguirá creando las interfaces de los métodos que se utilizarán en la capa service, en este caso serán 3 interfaces, la de piezas, proveedores y suministra. En dichas interfaces se generará la cabecera de los métodos que implementarán las diferentes clases de la capa service y que posteriormente se desarrollarán y mapearán en los controladores, a continuación se introduce el código generado para las interfaces en desplegables.

```java
package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.CajerosDTO;

public interface CajerosDAO extends JpaRepository<CajerosDTO, Long>{

}

package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.VentaDTO;

public interface VentaDAO extends JpaRepository<VentaDTO, Long>{

}

package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.ProductosDTO;

public interface ProductosDAO extends JpaRepository<ProductosDTO, Long>{

}

package com.crud.springmaven.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;

public interface MaquinasRegistradorasDAO extends JpaRepository<MaquinasRegistradorasDTO, Long>{

}


```

Se proseguirá creando las interfaces de los métodos que se utilizarán en la capa service, en este caso serán 4 interfaces, que corresponderán a la de las entidades cajeros, ventas, productos y máquinas registradoras. En dichas interfaces se generará la cabecera de los métodos que implementarán las diferentes clases de la capa service y que posteriormente se desarrollarán y mapearán en los controladores, a continuación se introduce el código generado para las interfaces en desplegables.

<details>
  
<summary>Interface de Cajeros</summary>
  
<br>

```java

package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.CajerosDTO;

public interface CajerosService {

	//Listar los cajeros
	public List<CajerosDTO> listarCajeros();
	
	//Crear Cajero
    public CajerosDTO crearCajeros(CajerosDTO cajero);

    //Modificar Cajero
    public CajerosDTO modificarCajeros(CajerosDTO cajero);

    //Eliminar Cajero
    public void eliminarCajero(Long id);
    
    //Buscar Cajero
    public CajerosDTO buscarCajero(Long id);
	
}
  
```
  
</details>


<details>
  
<summary>Interface de Ventas</summary>
  
<br>

```java

package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.VentaDTO;

public interface VentaService {
	
	//Listar las ventas
	public List<VentaDTO> listarVentas();
	
	//Crear venta
    public VentaDTO crearVenta(VentaDTO venta);

    //Modificar venta
    public VentaDTO modificarVenta(VentaDTO venta);

    //Eliminar venta
    public void eliminarVenta(Long id);
    
    //Buscar venta
    public VentaDTO buscarVenta(Long id);	

}


```
  
</details>


<details>
  
<summary>Interface de Productos</summary>
  
<br>

```java

package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.ProductosDTO;

public interface ProductosService {
	
	//Listar los productos
	public List<ProductosDTO> listarProductos();
	
	//Crear producto
    public ProductosDTO crearProductos(ProductosDTO producto);

    //Modificar producto
    public ProductosDTO modificarProductos(ProductosDTO producto);

    //Eliminar producto
    public void eliminarProducto(Long id);
    
    //Buscar producto
    public ProductosDTO buscarProducto(Long id);

}	

```
  
</details>


<details>
  
<summary>Interface de Maquinas Registradoras</summary>
  
<br>

```java

package com.crud.springmaven.Service;

import java.util.List;

import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;

public interface MaquinasRegistradorasService {

	//Listar las máquinas registradoras
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras();
		
	//Crear máquina registradora
	public MaquinasRegistradorasDTO crearMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora);
		
	//Modificar máquina registradora
	public MaquinasRegistradorasDTO modificarMaquinaRegistradora(MaquinasRegistradorasDTO maquinaRegistradora);

	//Eliminar máquina registradora
	public void eliminarMaquinaRegistradora(Long id);
	    
	//Buscar máquina registradora
	public MaquinasRegistradorasDTO buscarMaquinaRegistradora(Long id);
	    
} 

```
  
</details>

Completadas ya las interfaces se procederá a la creación de las clases que las implementarán. Una vez credas se definirá la anotación @Service indicando que esta clase pertenece a la capa de servicios y la anotación @Autowired que inyectará las dependencias del Jpa heredadas del dao. Una vez definido el 'implements' en la cabecera de la clase, eclipse pedirá aplicar los métodos de las interfaces.

Se rellenará el cuerpo de los métodos con los recibidos por Jpa, Jpa tiene funciones própias de CRUD, a continuación hay cuatro desplegables con el código generado en las diferentes clases de la capa service.

<details>

<summary>Clase capa Service Cajeros</summary>

<br>

```java

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

  
```

</details>


<details>

<summary>Clase capa Service Ventas</summary>

<br>

```java

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
  
```

</details>



<details>

<summary>Clase capa Service Productos</summary>

<br>

```java
  
package com.crud.springmaven.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.springmaven.DAO.ProductosDAO;
import com.crud.springmaven.DTO.ProductosDTO;

@Service 

public class ProductosServiceImpl implements ProductosService{

	@Autowired
	ProductosDAO productosDAO;
	
	@Override
	public List<ProductosDTO> listarProductos() {
		return productosDAO.findAll();
	}

	@Override
	public ProductosDTO crearProductos(ProductosDTO producto) {
		return productosDAO.save(producto);
	}

	@Override
	public ProductosDTO modificarProductos(ProductosDTO producto) {
		return productosDAO.save(producto);

	}

	@Override
	public void eliminarProducto(Long id) {
	}

	@Override
	public ProductosDTO buscarProducto(Long id) {
		return productosDAO.findById(id).get();
	}

}

```

</details>



<details>

<summary>Clase capa Service Maquinas Registradoras</summary>
  
<br>

```java
  
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
	public void eliminarMaquinaRegistradora(Long id) {		
	}

	@Override
	public MaquinasRegistradorasDTO buscarMaquinaRegistradora(Long id) {
		return maquinasRegistradorasDAO.findById(id).get();
	}
	
}


```

</details>


<details>

<summary></summary>
  
<br>

```java

```

</details>
