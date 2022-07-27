use ferreteria;

create table if not exists producto(
	id int unique,
    nombre varchar(30),
    precio double,
    categoria varchar(30),
    cantidad int
);
