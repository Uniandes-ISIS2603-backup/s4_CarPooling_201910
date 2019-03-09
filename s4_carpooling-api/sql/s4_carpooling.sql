delete from AlquilerEntity;
delete from SeguroEntity;
delete from PeajeEntity;

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

