delete from AlquilerEntity;
delete from SeguroEntity;
delete from PeajeEntity;
delete from UsuarioEntity;
delete from PagoEntity;
delete from InfoTCEntity;
delete from VehiculoEntity;
delete from TrayectoEntity;
delete from CalificacionEntity;
delete from NotificacionEntity;


delete from USUARIOENTITY_TRAYECTOENTITY;
delete from USUARIOENTITY_VEHICULOENTITY;

insert into AlquilerEntity (id, nombre) values (100,'alquiler1');
insert into AlquilerEntity (id, nombre) values (200,'alquiler2');
insert into AlquilerEntity (id, nombre) values (300,'alquiler1');
insert into AlquilerEntity (id, nombre) values (400,'alquiler2');

insert into SeguroEntity (id, tipo) values (100,'Diamante');
insert into SeguroEntity (id, tipo) values (200,'Platino');
insert into SeguroEntity (id, tipo) values (300,'Cuarzo');
insert into SeguroEntity (id, tipo) values (400,'Esmeralda');

INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Chinauta');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Mondoñedo');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Circasia');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Río Sogamoso	');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Cajamarca');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Honda');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Paso de La Torre');
INSERT INTO APP.PEAJEENTITY (COSTO, LATITUD, LONGITUD, NOMBRE) 
	VALUES (10000.1, 6.083496730640107, -72.63334461217967, 'Puerto Colombia');


insert into UsuarioEntity ( nombre, apellido,documento,celular,username,password,correo) 
values ('Daniel','Peña',1111111,21111111,'dan','APP','im.sarmientom@uniandes.edu.com');

insert into UsuarioEntity ( nombre, apellido,documento,celular,username,password,correo) 
values ('Isabela','Sarmiento',2222222,31111111,'isamasar','APP','1234@gmail.com');

insert into UsuarioEntity ( nombre, apellido,documento,celular,username,password,correo) 
values ('Julio','Morales',333333,41111111,'julio','APP','12345@gmail.com');

insert into UsuarioEntity ( nombre, apellido,documento,celular,username,password,correo) 
values ('Alejandro','Caicedo',444444,51111111,'latinalejo','APP','123456@gmail.com');

insert into UsuarioEntity ( nombre, apellido,documento,celular,username,password,correo) 
values ('Juan Felipe','García',555555,61111111,'juafelipe','APP','1234567@gmail.com');


insert into PagoEntity (id, valor)
values (100, 10);

insert into PagoEntity (id, valor)
values (200, 2);

insert into PagoEntity (id, valor)
values (300, 15);

insert into InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (100, '87653432156','12323434556','BancoBogota','Davivienda');

insert into InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (200, '34576567809','12323432123','Bancolombia','Itahu');

insert into InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (300, '98045667832','13243253478','Itahu','BancoBogota');

insert into VehiculoEntity (id, alquilado, color, modelo, placa)
values (100, 100,'Rojo','Ford','QWE102');

insert into VehiculoEntity (id, alquilado, color, modelo, placa)
values (200, 200,'Verde','Dodge','ELD950');

insert into VehiculoEntity (id, alquilado, color, modelo, placa)
values (300, 100,'Azul','BMW','KJU798');

insert into CalificacionEntity (id, puntaje, comentario)
values (100, 5,'Azul');

insert into CalificacionEntity (id, puntaje, comentario)
values (200, 4,'Azul2');

insert into CalificacionEntity (id, puntaje, comentario)
values (300, 3,'Azul3');


insert into NotificacionEntity (id, mensaje)
values (100, 'mensaje escrito');

insert into NotificacionEntity (id, mensaje)
values (200, 'mensaje escrito2');

insert into NotificacionEntity (id, mensaje)
values (300, 'mensaje escrito3');


insert into TrayectoEntity( fechafinal, fechainicial, estado ) values
('05/02/2019','09/20/1948', 0);


Insert Into TrayectoInfoEntity(costo, duracion, combustible, horaFinal, horaInicial)values
(24,24, 24, '20:20:20','10:10:10');


Insert Into TrayectoInfoEntity(costo, duracion, combustible, horaFinal, horaInicial)values
(42,42, 42, '20:20:20','10:10:10');


INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (4, -74, 'Bogotá', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (3, -76, 'Cali', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (6, -75, 'Medellin', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (10, -74, 'Barranquilla', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (10, -75, 'Cartagena', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (7, -72, 'Cúcuta', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (7, -73, 'Bucaramanga', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (4, -75, 'Pereira', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (11, -74, 'Santa Marta', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (4, -75, 'Ibagué', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (6, -75, 'Bello', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (1, -77, 'Pasto', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (5, -75, 'Manizales', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (2, -75, 'Neiva', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (4, -73, 'Villavicencio', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (4, -75, 'Armenia', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (10, -73, 'Valledupar', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (8, -75, 'Montería', NULL, NULL);
INSERT INTO APP.CIUDADENTITY (LAT, LON, NOMBRE, DESTINO_ID, ORIGEN_ID) 
	VALUES (8, -75, 'Ayapel', NULL, NULL);
