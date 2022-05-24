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

