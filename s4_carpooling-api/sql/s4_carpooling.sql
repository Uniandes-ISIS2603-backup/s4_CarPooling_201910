delete from AlquilerEntity;
delete from SeguroEntity;
delete from PeajeEntity;
delete from UsuarioEntity;
delete from PagoEntity;
delete from InfoTCEntity;

insert into AlquilerEntity (id, nombre) values (100,'alquiler1');
insert into AlquilerEntity (id, nombre) values (200,'alquiler2');
insert into AlquilerEntity (id, nombre) values (300,'alquiler1');
insert into AlquilerEntity (id, nombre) values (400,'alquiler2');

insert into SeguroEntity (id, tipo) values (100,'Diamante');
insert into SeguroEntity (id, tipo) values (200,'Platino');
insert into SeguroEntity (id, tipo) values (300,'Cuarzo');
insert into SeguroEntity (id, tipo) values (400,'Esmeralda');

insert into PeajeEntity (id, costo, nombre,latitud, longitud) values (100,1000.1,'Guajira',6.083496730640107,-72.63334461217967);
insert into PeajeEntity (id, costo, nombre,latitud, longitud) values (200,1000.1,'Guajira1',6.083496730640107,-72.63334461217967);
insert into PeajeEntity (id, costo, nombre,latitud, longitud) values (300,1000.1,'Guajira2',6.083496730640107,-72.63334461217967);
insert into PeajeEntity (id, costo, nombre,latitud, longitud) values (400,1000.1,'Guajira3',6.083496730640107,-72.63334461217967);

insert into UsuarioEntity (id, nombre, apellido,documento,celular,username,password,correo) 
values (100,'Daniel','Peña',1111111,21111111,'dan1','APP','123@gmail.com');

insert into UsuarioEntity (id, nombre, apellido,documento,celular,username,password,correo) 
values (200,'Isabela','Sarmiento',2222222,31111111,'IsaPro','1s4b3ll4','1234@gmail.com');

insert into UsuarioEntity (id, nombre, apellido,documento,celular,username,password,correo) 
values (300,'Julio','Morales',333333,41111111,'juliNightmare','juli1234','12345@gmail.com');

insert into PagoEntity (id, valor)
values (100, 10);

insert into PagoEntity (id, valor)
values (200, 2);

insert into PagoEntity (id, valor)
values (300, 15);

insert info InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (100, '87653432156','12323434556','BancoBogota','Davivienda');

insert info InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (200, '34576567809','12323432123','Bancolombia','Itahu');

insert info InfoTCEntity (id, t1, t2, entidad1, entidad2)
values (300, '98045667832','13243253478','Itahu','BancoBogota');
