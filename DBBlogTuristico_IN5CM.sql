drop database if exists DBBlogTuristico_IN5CM;
create database DBBlogTuristico_IN5CM;
use DBBlogTuristico_IN5CM;

create table usuarios(
	id_usuario int primary key not null auto_increment,
    username varchar(50) not null,
    nombre_usuario varchar(30) not null,
    apellido_usuario varchar(30) not null,
    email_usuario varchar(100) not null,
    contrasena_usuario varchar(50) not null,
    rol varchar(10) not null,
    fecha_registro date not null
);

create table Categorias (
    id_categoria int not null auto_increment,
    nombre_categoria  varchar(50)  not null,
    descripcion text,
    primary key (id_categoria)
);

create table Fotos(
	id_foto int not null auto_increment,
    titulo_foto varchar(50),
    descripcion text,
    foto blob,
    fecha_creacion date,
    id_usuario int not null,
    primary key (id_foto)
);

create table publicaciones(
	id_publicacion int primary key auto_increment not null,
    nombre_publicacion varchar(100) not null,
    descripcion text not null,
    direccion varchar(50) not null,
    foto blob,
    telefono int not null,
    email_publicacion varchar(100) not null,
    horario varchar(100) not null,
    fecha_creacion date not null,
    estado_publicacion varchar(100) not null,
    id_categoria int not null,
    id_usuario int not null,
    constraint FK_id_categoria foreign key  (id_categoria)
    references Categorias(id_categoria) on delete cascade,
    constraint FK_id_usuario foreign key (id_usuario)
    references usuarios(id_usuario) on delete cascade 
    
);

create table Reportes(
	id_reporte int auto_increment not null,
    asunto varchar(50) not null,
    mensaje text not null,
    fecha_envio datetime not null,
    id_usuario int not null,
    primary key PK_id_reporte(id_reporte),
    constraint FK_id_usuarios foreign key (id_usuario)
    references Usuarios(id_usuario) on delete cascade
);

create table Contactar(
	id_contactar int auto_increment not null,
    asunto varchar(50) not null,
    mensaje text not null,
    fecha_envio datetime not null,
    id_usuario int not null,
    id_publicacion int not null,
    primary key PK_id_contactar(id_contactar),
    constraint id_usuario_contacto foreign key (id_usuario)
    references Usuarios(id_usuario) on delete cascade,
    constraint FK_id_publicacion foreign key(id_publicacion)
    references Publicaciones(id_publicacion) on delete cascade
);

create table solicitud_publicacion(
	id_solicitud int auto_increment not null,
    fecha_solicitud date not null,
    estado varchar(10) not null,
    descripcion text not null,
    id_publicacion int not null,
    id_usuario int not null,
    primary key PK_id_solicitud(id_solicitud),
    constraint id_usuario_publicacion foreign key (id_usuario)
    references Usuarios(id_usuario) on delete cascade,
    constraint id_publicacion_solicitud foreign key(id_publicacion)
    references Publicaciones(id_publicacion) on delete cascade
);

create table Servicios(
	id_servicio int auto_increment not null,
    nombre_servicio varchar(50) not null,
    descripcion text not null,
    telefono int not null,
    foto blob,
    fecha_creacion date not null,
    id_usuario int not null,
    primary key PK_id_servicio (id_servicio),
	constraint id_usuario_servicio foreign key (id_usuario)
    references Usuarios(id_usuario) on delete cascade
);

create table Resenas(
	id_resena int primary key not null auto_increment,
    titulo_resena varchar(50) not null,
    comentario text not null,
    calificacion int not null,
    fecha_creacion date not null,
    id_usuario int not null,
    id_publicacion int not null,
    constraint id_usuario_resena foreign key (id_usuario)  
	references Usuarios (id_usuario) on delete cascade,
    constraint id_publicacion_resena foreign key (id_publicacion)  
	references Publicaciones (id_publicacion) on delete cascade
);

create table Eventos(
	id_evento int primary key not null auto_increment,
    nombre_evento varchar (50) not null,
    descripcion text not null,
    foto blob,
    fecha_inicio datetime not null,
    fecha_fin datetime not null,
    ubicacion varchar (100) not null,
    id_usuario int not null,
    constraint id_usuario_evento foreign key (id_usuario)  
	references Usuarios (id_usuario) on delete cascade 
);


create table favoritos(
	id_favorito int not null auto_increment,
    id_usuario int not null,
    id_publicacion int not null,
    id_categoria int not null,
    primary key (id_favorito),
    constraint fk_favorito_categoria foreign key (id_categoria) 
	references Categorias (id_categoria) on delete cascade,
    constraint fk_favorito_usuario foreign key (id_usuario) 
	references Categorias (id_categoria) on delete cascade,
    constraint fk_publicacion_fav foreign key (id_publicacion) 
	references Categorias (id_categoria) on delete cascade
);

-- SP USUARIOS

delimiter $$
create procedure sp_usuarios_read()
begin
    select * from usuarios;
end$$
delimiter ;

delimiter $$
create procedure sp_usuarios_buscar(in p_id int)
begin 
	select * from usuarios where id_usuario = p_id;
end$$
delimiter ;

delimiter $$
	create procedure sp_usuarios_create(
		p_username varchar(50),
        p_nombre_usuario varchar(30),
        p_apellido_usuario varchar(30),
        p_email_usuario varchar(100),
        p_contrasena_usuario varchar(50),
        p_rol varchar(10),
        p_fecha_registro date
	)
    begin
		insert into usuarios(username,nombre_usuario,apellido_usuario,email_usuario,contrasena_usuario,rol,fecha_registro)
        values (p_username,p_nombre_usuario,p_apellido_usuario,p_email_usuario,p_contrasena_usuario,p_rol,p_fecha_registro);
    end$$
delimiter ;

delimiter $$
create procedure sp_usuarios_update(
    p_id_usuario int,
    p_username varchar(50),
    p_nombre_usuario varchar(30),
    p_apellido_usuario varchar(30),
    p_email_usuario varchar(100),
    p_contrasena_usuario varchar(50),
    p_rol varchar(10),
    p_fecha_registro date
)
begin
    update usuarios 
    set username = p_username, 
        nombre_usuario = p_nombre_usuario, 
        apellido_usuario = p_apellido_usuario, 
        email_usuario = p_email_usuario, 
        contrasena_usuario = p_contrasena_usuario, 
        rol = p_rol, 
        fecha_registro = p_fecha_registro
    where id_usuario = p_id_usuario;
end$$
delimiter ;

delimiter $$
create procedure sp_usuarios_delete(
    p_id_usuario int
)
begin
    delete from usuarios where id_usuario = p_id_usuario;
end$$
delimiter ;

-- SP PUBLICACIONES

delimiter $$
create procedure sp_publicaciones_read()
begin
    select * from publicaciones;
end$$
delimiter ;

delimiter $$
create procedure sp_publicaciones_buscar(in p_id int)
begin 
	select * from publicaciones where id_publicacion = p_id;
end $$
delimiter

delimiter $$
create procedure sp_publicaciones_create(
    p_nombre_publicacion varchar(100),
    p_descripcion text,
    p_direccion varchar(50),
    p_foto blob,
    p_telefono int,
    p_email_publicacion varchar(100),
    p_horario varchar(100),
    p_fecha_creacion date,
    p_estado_publicacion varchar(100),
    p_id_categoria int,
    p_id_usuario int
)
begin
    insert into publicaciones(nombre_publicacion, descripcion, direccion, foto, telefono, email_publicacion, horario, fecha_creacion, estado_publicacion, id_categoria, id_usuario)
    values (p_nombre_publicacion, p_descripcion, p_direccion, p_foto, p_telefono, p_email_publicacion, p_horario, p_fecha_creacion, p_estado_publicacion, p_id_categoria, p_id_usuario);
end$$
delimiter ;

delimiter $$
create procedure sp_publicaciones_update(
    p_id_publicacion int,
    p_nombre_publicacion varchar(100),
    p_descripcion text,
    p_direccion varchar(50),
    p_foto blob,
    p_telefono int,
    p_email_publicacion varchar(100),
    p_horario varchar(100),
    p_fecha_creacion date,
    p_estado_publicacion varchar(100),
    p_id_categoria int,
    p_id_usuario int
)
begin
    update publicaciones 
    set nombre_publicacion = p_nombre_publicacion,
        descripcion = p_descripcion,
        direccion = p_direccion,
        foto = p_foto,
        telefono = p_telefono,
        email_publicacion = p_email_publicacion,
        horario = p_horario,
        fecha_creacion = p_fecha_creacion,
        estado_publicacion = p_estado_publicacion,
        id_categoria = p_id_categoria,
        id_usuario = p_id_usuario
    where id_publicacion = p_id_publicacion;
end$$
delimiter ;

delimiter $$
create procedure sp_publicaciones_delete(
    p_id_publicacion int
)
begin
    delete from publicaciones where id_publicacion = p_id_publicacion;
end$$
delimiter ;

-- CATEGORIAS --

delimiter $$
create procedure sp_mostrar_categorias()
begin
    select * from Categorias;
end$$
delimiter ;

delimiter $$
create procedure sp_agregar_categorias(
    in p_nombre_categoria varchar(50),
    in p_descripcion text
)
begin
    insert into Categorias (nombre_categoria, descripcion)
    values (p_nombre_categoria, p_descripcion);
end$$
delimiter ;

delimiter $$
create procedure sp_buscar_categorias(
    in p_id_categoria int
)
begin
    select * from Categorias
    where id_categoria = p_id_categoria;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_categorias(
    in p_id_categoria int,
    in p_nombre_categoria varchar(50),
    in p_descripcion text
)
begin
    update Categorias
    set nombre_categoria = p_nombre_categoria,
        descripcion = p_descripcion
    where id_categoria = p_id_categoria;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_categorias(
    in p_id_categoria int
)
begin
    delete from Categorias
    where id_categoria = p_id_categoria;
end$$
delimiter ;

-- FOTOS --

delimiter $$
create procedure sp_mostrar_fotos()
begin
    select * from Fotos;
end$$
delimiter ;

delimiter $$
create procedure sp_agregar_fotos(
    in p_titulo_foto varchar(50),
    in p_descripcion text,
    in p_foto longblob,
    in p_fecha_creacion date,
    in p_id_usuario int
)
begin
    insert into Fotos (titulo_foto, descripcion, foto, fecha_creacion, id_usuario)
    values (p_titulo_foto, p_descripcion, p_foto, p_fecha_creacion, p_id_usuario);
end$$
delimiter ;

delimiter $$
create procedure sp_buscar_fotos(
    in p_id_foto int
)
begin
    select * from Fotos
    where id_foto = p_id_foto;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_fotos(
    in p_id_foto int,
    in p_titulo_foto varchar(50),
    in p_descripcion text,
    in p_foto longblob,
    in p_fecha_creacion date,
    in p_id_usuario int
)
begin
    update Fotos
    set titulo_foto = p_titulo_foto,
        descripcion = p_descripcion,
        foto = p_foto,
        fecha_creacion = p_fecha_creacion,
        id_usuario = p_id_usuario
    where id_foto = p_id_foto;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_fotos(
    in p_id_foto int
)
begin
    delete from Fotos
    where id_foto = p_id_foto;
end$$
delimiter ;

-- FAVORITOS --

delimiter $$
create procedure sp_mostrar_favoritos()
begin
    select * from Favoritos;
end$$
delimiter ;

delimiter $$
create procedure sp_agregar_favoritos(
    in p_id_usuario int,
    in p_id_publicacion int,
    in p_id_categoria int
)
begin
    insert into Favoritos (id_usuario, id_publicacion, id_categoria)
    values (p_id_usuario, p_id_publicacion, p_id_categoria);
end$$
delimiter ;

delimiter $$
create procedure sp_buscar_favoritos(
    in p_id_favorito int
)
begin
    select * from Favoritos
    where id_favorito = p_id_favorito;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_favoritos(
    in p_id_favorito int,
    in p_id_usuario int,
    in p_id_publicacion int,
    in p_id_categoria int
)
begin
    update Favoritos
    set id_usuario = p_id_usuario,
        id_publicacion = p_id_publicacion,
        id_categoria = p_id_categoria
    where id_favorito = p_id_favorito;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_favoritos(
    in p_id_favorito int
)
begin
    delete from Favoritos
    where id_favorito = p_id_favorito;
end$$
delimiter ;

-- PROCEDIMIENTOS DE EVENTOS

delimiter $$
create procedure sp_Resenas_create (
	p_titulo_resena varchar (50),
    p_comentario text,
    p_calificacion int,
    p_fecha_creacion date,
    p_id_usuario int,
    p_id_publicacion int
)
begin
	insert into Resenas(titulo_resena, comentario, calificacion, fecha_creacion, id_usuario, id_publicacion)
    values (p_titulo_resena, p_comentario, p_calificacion, p_fecha_creacion, p_id_usuario, p_id_publicacion);
end$$
delimiter ;

delimiter $$
create procedure sp_Resenas_read_all ()
begin
	select * from Resenas order by id_resena;
end$$
delimiter ;

delimiter $$
create procedure sp_Resenas_delete(in p_idRe int)
begin
	delete from Resenas where id_resena = p_idRe;
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_Resenas_update(
	in p_id_resena int,
    in p_titulo_resena varchar (50),
    in p_comentario text,
    in p_calificacion int,
    in p_fecha_creacion date,
    in p_id_usuario int,
    in p_id_publicacion int
)
begin
	update Resenas
    set id_resena = p_id_resena,
		titulo_resena = p_titulo_resena,
        comentario = p_comentario,
        calificaion = p_calificacion,
        fecha_creacion = p_fecha_creacion,
        id_usuario = p_id_usuario,
        id_publicacion = p_id_publicacion
	where id_resena = p_id_resena;
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
	create procedure sp_Resenas_search (in id_resena int)
    begin
		select
			r.id_resena,
            r.titulo_resena,
            r.comentario,
            r.calificacion,
            r.fecha_creacion,
            r.id_usuario,
            r.id_publicacion
		from Resenas r where r.id_resena = id_resena;
    end $$
delimiter ;

-- PROCEDIMIENTOS DE EVENTOS

delimiter $$
create procedure sp_Eventos_create (
	p_nombre_evento varchar (50),
    p_descripcion text,
    p_foto blob,
    p_fecha_inicio datetime,
    p_fecha_fin datetime,
    p_ubicacion varchar (100),
    p_id_usuario int
)
begin
	insert into Eventos(nombre_evento, descripcion, foto, fecha_inicio, fecha_fin, ubicacion, id_usuario)
    values (p_nombre_evento, p_descripcion, p_foto, p_fecha_inicio, p_fecha_fin, p_ubicacion, p_id_usuario);
end$$
delimiter ;

delimiter $$
create procedure sp_Eventos_read_all ()
begin
	select * from Eventos order by id_eventos;
end$$
delimiter ;

delimiter $$
create procedure sp_Eventos_delete(in p_idEv int)
begin
	delete from Eventos where id_evento = p_idEv;
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_Eventos_update(
	in p_id_evento int,
    in p_nombre_evento varchar (50),
    in p_descripcion text,
    in p_foto blob,
    in p_fecha_inicio datetime,
    in p_fecha_fin datetime,
    in p_ubicacion varchar (100),
    in p_id_usuario int
)
begin
	update Eventos
    set id_evento = p_id_evento,
		nombre_evento = p_nombre_evento,
        descripcion = p_descripcion,
        foto = p_foto,
        fecha_inicio = p_fecha_inicio,
        fecha_fin = p_fecha_fin,
        ubicacion = p_ubicacion,
        id_usuario = p_id_usuario
	where id_evento = p_id_evento;
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
	create procedure sp_Eventos_search (in id_evento int)
    begin
		select
			e.id_evento,
            e.nombre_evento,
            e.descripcion,
            e.foto,
            e.fecha_inicio,
			e.fecha_fin,
            e.ubicacion,
            e.id_usuario
		from Eventos e where e.id_evento = id_evento;
    end $$
delimiter ;

-- PROCEDIMIENTOS DE REPORTES
delimiter $$
	create procedure sp_mostrar_reportes()
    begin
		select * from Reportes;
    end $$
delimiter ;

delimiter $$
	create procedure sp_agregar_reportes(
		in p_asunto varchar(50),
        in p_mensaje text,
        in p_fecha_envio datetime,
        in p_id_usuario int
    )
    begin
		insert into Reportes(asunto, mensaje, fecha_envio, id_usuario) values
        (p_asunto, p_mensaje, p_fecha_envio, p_id_usuario);
    end $$
delimiter ;
    
delimiter $$
	create procedure sp_actualizar_reportes(
		in p_id int,
		in p_asunto varchar(50),
        in p_mensaje text,
        in p_fecha_envio datetime,
        in p_id_usuario int
    )
    begin
		update Reportes r set r.asunto = p_asunto, r.mensaje = p_mensaje, r.fecha_envio = p_fecha_envio,
        r.id_usuario = p_id_usuario where r.id_reporte = p_id;
    end $$
delimiter $$

delimiter $$
	create procedure sp_eliminar_reportes(in p_id int)
    begin
		delete from Reportes where id_reporte = p_id;
    end $$
delimiter ;

delimiter $$
	create procedure sp_buscar_reportes(in p_id int)
    begin 
		select * from Reportes where id_reportes = p_id;
	end $$
delimiter ;

-- PROCEDIMIENTOS DE CONTACTAR
delimiter $$
	create procedure sp_mostrar_contactar()
    begin
		select * from Contactar;
    end $$
delimiter ;

delimiter $$
	create procedure sp_agregar_Contactar(
		in p_asunto varchar(50),
        in p_mensaje text,
        in p_fecha_envio datetime,
        in p_id_usuario int,
        in p_id_publicacion int
    )
    begin
		insert into Contactar(asunto, mensaje, fecha_envio, id_usuario, id_publicacion) values
        (p_asunto, p_mensaje, p_fecha_envio, p_id_usuario, p_id_publicacion);
    end $$
delimiter ;
    
delimiter $$
	create procedure sp_actualizar_contactar(
		in p_id int,
		in p_asunto varchar(50),
        in p_mensaje text,
        in p_fecha_envio datetime,
        in p_id_usuario int,
        in p_id_publicacion int
    )
    begin
		update Contactar c set c.asunto = p_asunto, c.mensaje = p_mensaje, c.fecha_envio = p_fecha_envio,
        c.id_usuario = p_id_usuario, c.id_publicacion = p_id_publicacion where c.id_contactar = p_id;
    end $$
delimiter $$

delimiter $$
	create procedure sp_eliminar_contactar(in p_id int)
    begin
		delete from Contactar where id_contactar = p_id;
    end $$
delimiter ;

delimiter $$
	create procedure sp_buscar_contactar(in p_id int)
    begin 
		select * from Contactar where id_contactar = p_id;
	end $$
delimiter ;


-- PROCEDIMIENTOS DE SOLICITUD PUBLICACION
delimiter $$
	create procedure sp_mostrar_solicitud()
    begin 
		select * from solicitud_publicacion;
    end $$
delimiter ;

delimiter $$
	create procedure sp_agregar_solicitud(
		in p_fecha_solicitud date,
        in p_estado varchar(10),
        in p_descripcion text,
        in p_id_publicacion int,
        in id_usuario int
    )
    begin 
		insert into solicitud_publicacion(fecha_solicitud, estado, descripcion, id_publicacion, id_usuario)
        values (p_fecha_solicitud, p_estado, p_descripcion, p_id_publicacion, id_usuario);
    end $$
delimiter ;

delimiter $$
	create procedure sp_actualizar_solicitud(
		in p_id int,
        in p_fecha_solicitud date,
        in p_estado varchar(10),
        in p_descripcion text,
        in p_id_publicacion int,
        in id_usuario int
    )
    begin 
		update solicitud_publicacion s set s.fecha_solicitud = p_fecha_solicitud, 
        s.estado = p_estado, s.descripcion = p_descripcion, s.id_publicacion = p_id_publicacion,
        s.id_usuario = p_id_usuario where s.id_solicitud = p_id_solicitud;
    end $$
delimiter ;

delimiter $$
	create procedure sp_eliminar_solicitud(in p_id int)
    begin 
		delete from solicitud_publicacion where id_solicitud = p_id;
    end $$
delimiter ;

delimiter $$
	create procedure sp_buscar_solicitud(in p_id int)
    begin
		select * from solicitud_publicacion where id_solicitud = p_id;
    end $$
delimiter ;

-- PROCEDIMIENTOS DE SERVICIOS 
delimiter $$
	create procedure sp_mostrar_servicios()
	begin
		select * from Servicios;
	end $$
delimiter ;

delimiter $$
	create procedure sp_agregar_servicios(
		in p_nombre_servicio varchar(50),
		in p_descripcion text,
		in p_telefono int,
		in p_foto blob,
		in p_fecha_creacion date,
		in p_id_usuario int
	)
	begin
		insert into Servicios(nombre_servicio, descripcion, telefono, foto, fecha_creacion, id_usuario)
		values(p_nombre_servicio, p_descripcion, p_telefono, p_foto, p_fecha_creacion, p_id_usuario);
	end $$
delimiter ;

delimiter $$
	create procedure sp_actualizar_servicios(
		in p_id_servicio int,
		in p_nombre_servicio varchar(50),
		in p_descripcion text,
		in p_telefono int,
		in p_foto blob,
		in p_fecha_creacion date,
		in p_id_usuario int
	)
	begin
		update Servicios s set s.nombre_servicio = p_nombre_servicio, s.descripcion = p_descripcion,
			s.telefono = p_telefono, s.foto = p_foto, s.fecha_creacion = p_fecha_creacion,
			s.id_usuario = p_id_usuario where s.id_servicio = p_id_servicio;
	end $$
delimiter ;

delimiter $$
	create procedure sp_eliminar_servicios(in p_id int)
	begin
		delete from Servicios where id_servicio = p_id;
	end $$
delimiter ;

delimiter $$
	create procedure sp_buscar_servicios(in p_id int)
	begin
		select * from Servicios where id_servicio = p_id;
	end $$
delimiter ;

-- INSERTS DE DATOS EN LAS TABLAS

-- registros usuarios
call sp_usuarios_create('roberto_MOD','Roberto','Gómez','roberto.g@empresa.com','Admin#2026','admin','2026-01-10');
call sp_usuarios_create('valeria_sun','Valeria','Solís','valeria.solis@mail.com','Val12345','usuario','2026-01-15');
call sp_usuarios_create('soporte_luis','Luis','Castañeda','l.castaneda@soporte.com','usuario','usuario','2026-01-20');
call sp_usuarios_create('elena_writer','Elena','Paz','elena.paz@blog.com','Write_2026','vendedor','2026-02-01');
call sp_usuarios_create('marcos_vip','Marcos','Rivas','m.rivas@premium.com','GoldPass!','usuario','2026-02-05');
call sp_usuarios_create('ventas_ana','Ana','López', 'ana.lopez@ventas.com','Sales_Ana88','usuario','2026-02-10');
call sp_usuarios_create('dev_tester','Diego','Torres','diego.dev@test.com','DevMode_On','vendedor','2026-02-12');
call sp_usuarios_create('mod_carla','Carla','Mendoza','c.mendoza@comunidad.com','Mod_Secure!','usuario','2026-02-14');
call sp_usuarios_create('pablo_edu','Pablo','Vargas', 'pvargas@universidad.edu','StudyHard26','usuario','2026-02-18');
call sp_usuarios_create('sara_mkt','Sara','Herrera','sara.h@marketing.com','Mkt_2026_Success','usuario','2026-02-22');

-- Registros categorias --
call sp_agregar_categorias('servicios', 'ofertas de servicios profesionales');
call sp_agregar_categorias('ventas', 'productos en venta');
call sp_agregar_categorias('educación', 'clases y cursos');
call sp_agregar_categorias('inmuebles', 'apartamentos y casas');
call sp_agregar_categorias('mascotas', 'servicios y productos para animales');
call sp_agregar_categorias('comida', 'restaurantes y menús');
call sp_agregar_categorias('tecnología', 'soporte técnico y dispositivos');
call sp_agregar_categorias('deportes', 'artículos deportivos');
call sp_agregar_categorias('jardinería', 'servicios de jardinería');
call sp_agregar_categorias('marketing', 'consultoría y asesoría digital');

-- registros publicaciones
call sp_publicaciones_create('Venta de Garage', 'Muebles y electrodomésticos en buen estado.', 'Calle 10 #5-20', '', 5551234, 'ventas@correo.com', '09:00 - 18:00', '2026-01-15', 'aceptado', 1, 1);
call sp_publicaciones_create('Fontanero Express', 'Reparación de tuberías y fugas de agua.', 'Av. Central 45', '', 5559876, 'plomeria@web.com', '24 Horas', '2026-01-18', 'pendiente', 2, 2);
call sp_publicaciones_create('Clases de Guitarra', 'Nivel básico e intermedio para niños y adultos.', 'Barrio San Juan', '', 5554433, 'musica@clases.com', '14:00 - 20:00', '2026-01-20', 'aceptado', 3, 3);
call sp_publicaciones_create('Apartamento Amueblado', '2 habitaciones, cerca del centro comercial.', 'Edificio Las Palmas', '', 5552211, 'rentas@inmuebles.com', 'Lunes a Viernes', '2026-01-25', 'aceptado', 4, 4);
call sp_publicaciones_create('Paseador de Perros', 'Caminatas diarias en parques locales.', 'Sector Norte', '', 5556677, 'canino@amigos.com', '07:00 - 11:00', '2026-02-01', 'pendiente', 5, 5);
call sp_publicaciones_create('Menú del Día', 'Almuerzos económicos con sabor casero.', 'Carrera 8 #12-40', '', 5553322, 'cocina@delicia.com', '11:30 - 15:00', '2026-02-05', 'pendiente', 6, 6);
call sp_publicaciones_create('Tecnico PC', 'Soporte técnico y mantenimiento preventivo.', 'Calle Florida 200', '', 5551122, 'tech@soporte.com', '08:00 - 17:00', '2026-02-10', 'aceptado', 2, 7);
call sp_publicaciones_create('Bicicleta de Montaña', 'Casi nueva, marco de aluminio, rin 29.', 'Residencial El Bosque', '', 5558899, 'marcos_bici@mail.com', 'Fines de semana', '2026-02-12', 'aceptado', 1, 8);
call sp_publicaciones_create('Jardines Verdes', 'Poda de césped y diseño de paisajes.', 'Zona Rural 5', '', 5550011, 'jardin@verdes.com', '06:00 - 14:00', '2026-02-15', 'pendiente', 5, 9);
call sp_publicaciones_create('Asesoría Digital', 'Estrategias para redes sociales y SEO.', 'Oficina Virtual', '', 5557788, 'mkt@estrategia.com', 'Cita previa', '2026-02-20', 'aceptado', 7, 10);

-- Fotos --
call sp_agregar_fotos('isla de flores - vista aérea', 'panorámica de la isla de flores en petén', null, '2026-02-20', 1);
call sp_agregar_fotos('isla de flores - atardecer', 'puesta de sol sobre el lago petén itzá', null, '2026-02-21', 2);
call sp_agregar_fotos('isla de flores - calles coloniales', 'calles empedradas y casas coloridas en la isla', null, '2026-02-22', 3);
call sp_agregar_fotos('isla de flores - muelle principal', 'vista del muelle con lanchas en el lago', null, '2026-02-23', 4);
call sp_agregar_fotos('isla de flores - iglesia histórica', 'fachada de la iglesia en el centro de la isla', null, '2026-02-24', 5);
call sp_agregar_fotos('isla de flores - paseo nocturno', 'luces reflejadas en el lago durante la noche', null, '2026-02-25', 6);
call sp_agregar_fotos('isla de flores - mercado local', 'puestos de artesanías y comida típica', null, '2026-02-26', 7);
call sp_agregar_fotos('isla de flores - vista desde el lago', 'la isla observada desde una lancha en el agua', null, '2026-02-27', 8);
call sp_agregar_fotos('isla de flores - puente de acceso', 'puente que conecta la isla con tierra firme', null, '2026-02-28', 9);
call sp_agregar_fotos('isla de flores - turismo cultural', 'visitantes recorriendo las calles y museos', null, '2026-03-01', 10);

-- Favoritos --
call sp_agregar_favoritos(1, 1, 2);  
call sp_agregar_favoritos(2, 2, 1);  
call sp_agregar_favoritos(3, 3, 3);   
call sp_agregar_favoritos(4, 4, 4);  
call sp_agregar_favoritos(5, 5, 5);   
call sp_agregar_favoritos(6, 6, 6);   
call sp_agregar_favoritos(7, 7, 7);   
call sp_agregar_favoritos(8, 8, 8);   
call sp_agregar_favoritos(9, 9, 9);   
call sp_agregar_favoritos(10, 10, 10);

call sp_agregar_Contactar('Consulta general', 'Quiero informacion del lugar', now(), 1, 1);
call sp_agregar_Contactar('Precio', 'Cual es el costo?', now(), 2, 2);
call sp_agregar_Contactar('Reservacion', 'Se puede reservar en linea?', now(), 3, 3);
call sp_agregar_Contactar('Ubicacion', 'Como llegar al sitio?', now(), 4, 4);
call sp_agregar_Contactar('Horario', 'Horario fines de semana?', now(), 5, 5);
call sp_agregar_Contactar('Promocion', 'Tienen descuentos?', now(), 6, 6);
call sp_agregar_Contactar('Guia', 'Incluye guia turistico?', now(), 7, 7);
call sp_agregar_Contactar('Transporte', 'Incluye transporte?', now(), 8, 8);
call sp_agregar_Contactar('Pago', 'Aceptan tarjeta?', now(), 9, 9);
call sp_agregar_Contactar('Disponibilidad', 'Hay cupo mañana?', now(), 10, 10);

call sp_agregar_solicitud(curdate(), 'PENDIENTE', 'Solicitud inicial', 1, 1);
call sp_agregar_solicitud(curdate(), 'APROBADO', 'Contenido validado', 2, 2);
call sp_agregar_solicitud(curdate(), 'RECHAZADO', 'Falta informacion', 3, 3);
call sp_agregar_solicitud(curdate(), 'PENDIENTE', 'En revision', 4, 4);
call sp_agregar_solicitud(curdate(), 'APROBADO', 'Cumple requisitos', 5, 5);
call sp_agregar_solicitud(curdate(), 'PENDIENTE', 'Revisando imagen', 6, 6);
call sp_agregar_solicitud(curdate(), 'APROBADO', 'Publicacion correcta', 7, 7);
call sp_agregar_solicitud(curdate(), 'RECHAZADO', 'Datos incompletos', 8, 8);
call sp_agregar_solicitud(curdate(), 'PENDIENTE', 'Esperando validacion', 9, 9);
call sp_agregar_solicitud(curdate(), 'APROBADO', 'Autorizada', 10, 10);

call sp_agregar_servicios('Hotel Colonial', 'Hospedaje en Antigua', 12345678, null, curdate(), 1);
call sp_agregar_servicios('Tour Tikal', 'Tour arqueologico', 23456789, null, curdate(), 2);
call sp_agregar_servicios('Restaurante Maya', 'Comida tipica', 34567890, null, curdate(), 3);
call sp_agregar_servicios('Guia Local', 'Guia certificado', 45678901, null, curdate(), 4);
call sp_agregar_servicios('Transporte VIP', 'Servicio privado', 56789012, null, curdate(), 5);
call sp_agregar_servicios('Hostal Centro', 'Hospedaje economico', 67890123, null, curdate(), 6);
call sp_agregar_servicios('Eco Tours', 'Turismo ecológico', 78901234, null, curdate(), 7);
call sp_agregar_servicios('Hotel Lago', 'Vista panoramica', 89012345, null, curdate(), 8);
call sp_agregar_servicios('Camping Peten', 'Zona natural', 90123456, null, curdate(), 9);
call sp_agregar_servicios('Agencia Viajes GT', 'Paquetes turísticos', 11223344, null, curdate(), 10);

call sp_Resenas_create('Excelente', 'Muy buena experiencia', 5, curdate(), 1, 1);
call sp_Resenas_create('Muy bueno', 'Recomendado', 4, curdate(), 2, 2);
call sp_Resenas_create('Regular', 'Puede mejorar', 3, curdate(), 3, 3);
call sp_Resenas_create('Increible', 'Volveria otra vez', 5, curdate(), 4, 4);
call sp_Resenas_create('Aceptable', 'Buen servicio', 4, curdate(), 5, 5);
call sp_Resenas_create('Malo', 'No cumplio expectativas', 2, curdate(), 6, 6);
call sp_Resenas_create('Fantastico', 'Me encanto el lugar', 5, curdate(), 7, 7);
call sp_Resenas_create('Bueno', 'Todo correcto', 4, curdate(), 8, 8);
call sp_Resenas_create('Normal', 'Nada especial', 3, curdate(), 9, 9);
call sp_Resenas_create('Excelente servicio', 'Totalmente recomendado', 5, curdate(), 10, 10);

call sp_agregar_servicios('hotel colonial', 'hospedaje centrico', 12345678, null, curdate(), 1);
call sp_agregar_servicios('tour tikal', 'recorrido arqueologico', 23456789, null, curdate(), 2);
call sp_agregar_servicios('restaurante maya', 'comida tipica guatemalteca', 34567890, null, curdate(), 3);
call sp_agregar_servicios('guia certificado', 'tour personalizado', 45678901, null, curdate(), 4);
call sp_agregar_servicios('transporte vip', 'traslado privado', 56789012, null, curdate(), 5);
call sp_agregar_servicios('hostal centro', 'hospedaje economico', 67890123, null, curdate(), 6);
call sp_agregar_servicios('eco tours', 'turismo ecologico', 78901234, null, curdate(), 7);
call sp_agregar_servicios('hotel lago', 'vista panoramica', 89012345, null, curdate(), 8);
call sp_agregar_servicios('camping peten', 'zona natural', 90123456, null, curdate(), 9);
call sp_agregar_servicios('agencia viajes gt', 'paquetes turisticos', 11223344, null, curdate(), 10);

call sp_agregar_reportes('contenido inapropiado', 'la publicacion contiene informacion falsa', curdate(), 1);
call sp_agregar_reportes('spam', 'publicacion repetida varias veces', curdate(), 2);
call sp_agregar_reportes('lenguaje ofensivo', 'comentarios irrespetuosos', curdate(), 3);
call sp_agregar_reportes('informacion incorrecta', 'datos de contacto erroneos', curdate(), 4);
call sp_agregar_reportes('imagen inapropiada', 'foto no relacionada', curdate(), 5);
call sp_agregar_reportes('ubicacion falsa', 'direccion incorrecta', curdate(), 6);
call sp_agregar_reportes('fraude', 'cobro no autorizado', curdate(), 7);
call sp_agregar_reportes('suplantacion', 'perfil falso', curdate(), 8);
call sp_agregar_reportes('publicidad engañosa', 'promocion falsa', curdate(), 9);
call sp_agregar_reportes('otro', 'contenido no adecuado', curdate(), 10);

call sp_Eventos_create('festival cultural antigua','evento tradicional con actividades artisticas',null,'2026-03-10 08:00:00','2026-03-10 18:00:00','antigua guatemala',1);
call sp_Eventos_create('feria gastronomica xela','degustacion de comida tipica',null,'2026-04-05 09:00:00','2026-04-05 20:00:00','quetzaltenango',2);
call sp_Eventos_create('concierto lago atitlan','musica en vivo frente al lago',null,'2026-05-12 17:00:00','2026-05-12 23:00:00','panajachel',3);
call sp_Eventos_create('expo turismo guatemala','promocion de destinos turisticos nacionales',null,'2026-06-01 08:00:00','2026-06-02 17:00:00','ciudad de guatemala',4);
call sp_Eventos_create('festival maya tikal','celebracion cultural ancestral',null,'2026-07-15 06:00:00','2026-07-15 16:00:00','tikal peten',5);
call sp_Eventos_create('maraton coban 10k','carrera deportiva anual',null,'2026-08-20 05:30:00','2026-08-20 12:00:00','coban',6);
call sp_Eventos_create('noche cultural chichi','presentaciones artisticas tradicionales',null,'2026-09-10 18:00:00','2026-09-10 22:00:00','chichicastenango',7);
call sp_Eventos_create('festival flores peten','actividades recreativas y culturales',null,'2026-10-05 10:00:00','2026-10-05 21:00:00','flores peten',8);
call sp_Eventos_create('festival navideño antigua','evento familiar con decoraciones navideñas',null,'2026-12-15 16:00:00','2026-12-15 22:00:00','antigua guatemala',9);
call sp_Eventos_create('festival verano monterrico','actividades recreativas en la playa',null,'2026-03-25 09:00:00','2026-03-25 19:00:00','monterrico',10);
