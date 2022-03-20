CREATE TABLE categoria
(
    id_categoria smallint primary key GENERATED ALWAYS AS IDENTITY,
    categoria varchar(50)
);

CREATE TABLE funko
(
  id_categoria smallint references categoria(categoria),
  nombre varchar(256),
  imagen varchar(256),
  precio varchar(20),
  descripcion varchar(256)
);








