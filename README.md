<table>
<tr>
<td width="100px"><img src="https://user-images.githubusercontent.com/103035621/170483040-a88d598b-145b-4903-accb-948ceff05811.png" alt="Team DOU"/></td>
<td width="1100px"> <h2>MSKA: Spring + JPA + MYSQL + Spring Security UD26_Ejercicio-3</h2> </td>

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

Para finalizar la parte java faltará crear los controladores, para ello en la capa controllers se definen los controladores piezas, proveedores y suministra. En la clase controller se tendrán que añadir las anotaciones @RestController para indicarle a spring que este controlador es del tipo rest, @RequestMapping("/api") para la raíz de la aplicación referente a los endpoints, la anotación @Autowired para inyectar las dependencias de la capa service definidos e implementados sobre la clase 'CajerosServiceImpl', 'VentaServiceImpl', 'MaquinasRegistradorasServiceImpl' y 'ProductosServiceImpl'.

Para finalizar con la explicación de las anotaciones se utilizarán los mapeos de método HTTP, dichas anotaciones son:

@GetMapping("/ruta api") --> Método utilizado para obtener datos.
@PostMapping("/ruta api") --> Método utilizado para crear algún tipo de registro.
@PutMapping("/ruta api") --> Método para actualizar registros.
@DeleteMapping("/ruta api") --> Método para eliminar un registro.

A continuación se generan los desplegables que hacen referencia a los controladores creados:
	

<details>

<summary>Código controlador Cajeros</summary>
  
<br>

```java

package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.CajerosDTO;
import com.crud.springmaven.Service.CajerosServiceImpl;


/**
 * Clase @CajerosController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class CajerosController {

	/** Se crea una instancia del tipo @AlmacenesServiceImpl */
	@Autowired
	CajerosServiceImpl cajerosServiceImpl;

	/** Método para listar cajeros */
	@GetMapping("/cajeros")
	public List<CajerosDTO> listarCajeros() {
		return cajerosServiceImpl.listarCajeros();
	}

	/** Método para crear un nuevo cajero */
	@PostMapping("/cajeros")
	public CajerosDTO crearCajero(@RequestBody CajerosDTO cajero) {
		return cajerosServiceImpl.crearCajeros(cajero);
	}
	
	/** Método para buscar un cajero por codigo */
	@GetMapping("/cajeros/{id}")
	public CajerosDTO buscarCajeroId(@PathVariable(name = "id") Long id) {
		return cajerosServiceImpl.buscarCajero(id);
	}
	
	/** Método para actualizar un cajero */
	@PutMapping("/cajeros/{id}")
	public CajerosDTO actualizarCajero(@PathVariable(name = "id") Long id,
			@RequestBody CajerosDTO cajeros) {
		
	/** Se definen instancias del tipo Cajeros */
	CajerosDTO cajero_a_actualizar = new CajerosDTO();
	CajerosDTO actualizado = new CajerosDTO();
		
	/** Se filtra el cajero a actualizar por código */
	cajero_a_actualizar = cajerosServiceImpl.buscarCajero(id);
		
	/** Se actualizan los valores */
	cajero_a_actualizar.setId(cajeros.getId());
	cajero_a_actualizar.setNomApels(cajeros.getNomApels());

	actualizado = cajerosServiceImpl.modificarCajeros(cajero_a_actualizar);

		return actualizado;
	}
	
	/** Método para eliminar un cajero */
	@DeleteMapping("/cajeros/{id}")
	public void eliminarCajero(@PathVariable(name = "id") Long id) {
		cajerosServiceImpl.eliminarCajero(id);
		System.out.println("Cajero eliminado con exito.");
	}
}
	
```

</details>
	
<details>

<summary>Código controlador Venta</summary>
  
<br>

```java
	
package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.VentaDTO;
import com.crud.springmaven.Service.VentaServiceImpl;

/**
 * Clase @VentaController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class VentaController {

	/** Se crea una instancia del tipo @VentaServiceImpl */
	@Autowired
	VentaServiceImpl ventaServiceImpl;
	
	/** Método para listar ventas */
	@GetMapping("/ventas")
	public List<VentaDTO> listarVentas() {
		return ventaServiceImpl.listarVentas();
	}

	/** Método para crear una nueva venta */
	@PostMapping("/ventas")
	public VentaDTO crearVenta(@RequestBody VentaDTO venta) {
		return ventaServiceImpl.crearVenta(venta);
	}
	
	/** Método para buscar una venta por codigo */
	@GetMapping("/ventas/{id}")
	public VentaDTO buscarVentaId(@PathVariable(name = "id") Long id) {
		return ventaServiceImpl.buscarVenta(id);
	}
	
	/** Método para actualizar una venta */
	@PutMapping("/ventas/{id}")
	public VentaDTO actualizarVenta(@PathVariable(name = "id") Long id,
			@RequestBody VentaDTO venta) {
		
	/** Se definen instancias del tipo Ventas */
	VentaDTO venta_a_actualizar = new VentaDTO();
	VentaDTO actualizado = new VentaDTO();
		
	/** Se filtra la venta a actualizar por código */
	venta_a_actualizar = ventaServiceImpl.buscarVenta(id);
		
	/** Se actualizan los valores */
	venta_a_actualizar.setId(venta.getId());
	venta_a_actualizar.setCajero(venta.getCajero());
	venta_a_actualizar.setMaquina(venta.getMaquina());
	venta_a_actualizar.setProducto(venta.getProducto());

	actualizado = ventaServiceImpl.modificarVenta(venta_a_actualizar);

		return actualizado;
	}
	
	/** Método para eliminar una venta */
	@DeleteMapping("/ventas/{id}")
	public void eliminarVenta(@PathVariable (name = "id") Long id) {
		ventaServiceImpl.eliminarVenta(id);
		System.out.println("Se ha eliminado la venta");
	}
}


```

</details>
	

<details>

<summary>Código controlador Máquinas Registradoras</summary>
  
<br>

```java
	
package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.MaquinasRegistradorasDTO;
import com.crud.springmaven.Service.MaquinasRegistradorasServiceImpl;

/**
 * Clase @MaquinasRegistradorasController. Mappea las funcionalidades con las
 * rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")
public class MaquinasRegistradorasController {

	/** Se crea una instancia del tipo @MaquinasRegistradorasServiceImpl */
	@Autowired
	MaquinasRegistradorasServiceImpl maquinasRegistradorasServiceImpl;

	/** Método para listar máquinas registradoras */
	@GetMapping("/maquinasregistradoras")
	public List<MaquinasRegistradorasDTO> listarMaquinasRegistradoras() {
		return maquinasRegistradorasServiceImpl.listarMaquinasRegistradoras();
	}

	/** Método para crear una nueva máquina registradora */
	@PostMapping("/maquinasregistradoras")
	public MaquinasRegistradorasDTO crearMaquinaRegistradora(@RequestBody MaquinasRegistradorasDTO maquinaRegistradora) {
		return maquinasRegistradorasServiceImpl.crearMaquinaRegistradora(maquinaRegistradora);
	}

	/** Método para buscar una máquina registradora por codigo */
	@GetMapping("/maquinasregistradoras/{id}")
	public MaquinasRegistradorasDTO buscarMaquinaRegistradoraId(@PathVariable(name = "id") Long id) {
		return maquinasRegistradorasServiceImpl.buscarMaquinaRegistradora(id);
	}

	/** Método para actualizar una máquina registradora */
	@PutMapping("/maquinasregistradoras/{id}")
	public MaquinasRegistradorasDTO actualizarMaquinaRegistradora(@PathVariable(name = "id") Long id,
			@RequestBody MaquinasRegistradorasDTO maquinaRegistradora) {

	/** Se definen instancias del tipo maquinasRegistradoras */
	MaquinasRegistradorasDTO maquinaRegistradora_a_actualizar = new MaquinasRegistradorasDTO();
	MaquinasRegistradorasDTO actualizado = new MaquinasRegistradorasDTO();

	/** Se filtra la máquina registradora a actualizar por código */
	maquinaRegistradora_a_actualizar = maquinasRegistradorasServiceImpl.buscarMaquinaRegistradora(id);
	
	/** Se actualizan los valores */
	maquinaRegistradora_a_actualizar.setId(maquinaRegistradora.getId());
	maquinaRegistradora_a_actualizar.setPiso(maquinaRegistradora.getPiso());

	actualizado = maquinasRegistradorasServiceImpl.modificarMaquinaRegistradora(maquinaRegistradora_a_actualizar);

		return actualizado;
	}
		
	/** Método para eliminar una caja registradora */
	@DeleteMapping("/maquinaregistradora/{id}")
	public void eliminarMaquinaRegistradora(@PathVariable(name = "id") Long id) {
		maquinasRegistradorasServiceImpl.eliminarMaquinaRegistradora(id);
		System.out.println("Máquina registradora eliminada con exito.");
	}
}

```

</details>
	
	
<details>

<summary>Código controlador Productos</summary>
  
<br>

```java
	
package com.crud.springmaven.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springmaven.DTO.ProductosDTO;
import com.crud.springmaven.Service.ProductosServiceImpl;

/**
 * Clase @ProductosController. Mappea las funcionalidades con las rutas de la api.
 * 
 * @author Octavio Bernal.
 * @author Ixabel Justo.
 * @author David Dalmau.
 * @author Josep Oriol López.
 *
 */

@RestController
@RequestMapping("/api")

public class ProductosController {
	
	/** Se crea una instancia del tipo @ProductosServiceImpl */
	@Autowired
	ProductosServiceImpl productosServiceImpl;

	/** Método para listar productos */
	@GetMapping("/productos")
	public List<ProductosDTO> listarProductos() {
		return productosServiceImpl.listarProductos();
	}

	/** Método para crear un nuevo producto */
	@PostMapping("/productos")
	public ProductosDTO crearProducto(@RequestBody ProductosDTO producto) {
		return productosServiceImpl.crearProductos(producto);
	}
	
	/** Método para buscar un producto por codigo */
	@GetMapping("/productos/{id}")
	public ProductosDTO buscarProductoId(@PathVariable(name = "id") Long id) {
		return productosServiceImpl.buscarProducto(id);
	}
	
	/** Método para actualizar un producto */
	@PutMapping("/productos/{id}")
	public ProductosDTO actualizarProducto(@PathVariable(name = "id") Long id,
			@RequestBody ProductosDTO productos) {
		
	/** Se definen instancias del tipo Productos */
	ProductosDTO producto_a_actualizar = new ProductosDTO();
	ProductosDTO actualizado = new ProductosDTO();
		
	/** Se filtra el producto a actualizar por código */
	producto_a_actualizar = productosServiceImpl.buscarProducto(id);
		
	/** Se actualizan los valores */
	producto_a_actualizar.setId(productos.getId());
	producto_a_actualizar.setNombre(productos.getNombre());
	producto_a_actualizar.setPrecio(productos.getPrecio());

	actualizado = productosServiceImpl.modificarProductos(producto_a_actualizar);

		return actualizado;
	}
	
	/** Método para eliminar un producto */
	@DeleteMapping("/productos/{id}")
	public void eliminarProducto(@PathVariable(name = "id") Long id) {
		productosServiceImpl.eliminarProducto(id);
		System.out.println("Producto eliminado con exito.");
	}
}

```

</details>
	
	
A estas alturas lo único que quedará será verificar el funcionamiento del aplicativo, para ello se utilizará postman para testear los endpoints de cada entidad. Lo primero de todo será proceder con la comprobación de obtener todos los datos de cajeros, venta, máquinas registradoras y productos, para ello se utilizará el método HTTP GET apuntando a la dirección del controlador referente a listar. En las siguientes tres imagenes se muestra el resultado:

<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170116215-a73fe6e4-484a-409c-b3bc-6fec31eebcf8.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170116251-49e8f9c7-0496-445f-bf74-d2efc61e11ec.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170116317-6218c530-6d3e-4b80-a6ca-cbae011dc852.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170116362-a0afae1c-c517-4187-b572-1f6923d7b2c5.PNG">
</p>


La siguiente verificación pasará por el endpoint de buscar un componente por identificador, para ello se utilizará el método HTTP GET apuntando a la dirección del controlador referente a listar. En las siguientes tres imagenes se muestra el resultado:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113955-60de650d-e954-48d9-83e9-4da60b490709.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113988-b3308d81-9d88-4739-95fd-cdd9363c8a99.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170114018-efd343c6-a2e0-4b09-b389-c444cad0928e.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170116907-117cff9f-4261-4a7e-8517-c085e00b8096.PNG">
</p>

El siguiente procedimiento de verificación constará de apuntar al endpoint de modificación de datos de entidad para ello se utilizará el método HTTP PUT indicando en la URI del endpoint el identificador del componentes y introduciendo en el body los datos a modificar en JSON. Se muestra el ejemplo en las siguientes capturas de pantalla:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170114899-aa16e109-61fe-47b6-8c3c-4d2297f2f965.PNG">
</p>

<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170114981-a34f7fee-606b-41d4-bc7b-c63e7b15c8f1.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115021-a791abad-f8e6-4456-9cdb-8103ed722f37.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115036-c27b03bd-96f9-4d4e-ac51-defb847fd9fa.PNG">
</p>
	
A continuación se verificará la eliminación de registros de las diferentes tablas, para ello se utilizará el método HTTP DELETE apuntando al endpoint correspondiente, en este caso también se pedirá al usuario que introduzca el identificador del componente a eliminar, al ser de tipo void no mostrará nada por pantalla, para mostrar correctamente la eliminación se muestra en consola un mensaje. Se puede visualizar el ejemplo en las diferentes capturas:
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115563-6869c704-dadb-426c-b50b-07a2374c2ea1.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115587-aa4b84a1-8c09-42f5-9d19-e3a8e15c9aef.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115611-833cebad-cab3-4fdc-8c3a-51d32cf8ab56.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170115634-b1a099d9-d029-4b7f-88ff-37f60b78195f.PNG">
</p>

Por último verificar la creación de nuevos elementos en las diferentes tablas, para ello se utiliza el método HTTP POST seguido de la ruta del endpoint, se ha de especificar en el body los datos del nuevo componente a crear. En las siguientes capturas se observa un ejemplo:

<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113279-04bf8ff7-31cb-4270-8f6a-f30fa84bb87c.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113291-767540d4-5b25-47c5-a98f-a5cca0f662e8.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113321-a6db35e9-a680-4042-896e-e0e8f1891ecf.PNG">
</p>
	
<p align="center">
	<img src="https://user-images.githubusercontent.com/103035621/170113355-6c78015b-d4fc-47f8-8b0b-1ff55a516584.PNG">
</p>
	
