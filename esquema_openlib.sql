CREATE USER ronildo WITH PASSWORD 'r123';
CREATE ROLE admin;
GRANT ALL PRIVILEGES ON DATABASE openlib TO admin;

CREATE USER convidado WITH PASSWORD 'c456';
CREATE ROLE onlyread;
GRANT SELECT ON DATABASE openlib TO onlyread;


CREATE TABLE Autor (
                id_autor SERIAL NOT NULL,
                nome_autor VARCHAR NOT NULL,
                CONSTRAINT id_autor PRIMARY KEY (id_autor)
);


CREATE TABLE Usuario (
                id_usuario SERIAL NOT NULL,
                nome VARCHAR NOT NULL,
                senha VARCHAR NOT NULL,
                email VARCHAR NOT NULL,                
                link_foto VARCHAR NULL,
                curso VARCHAR NULL,
                CONSTRAINT id_usuario PRIMARY KEY (id_usuario)
);


CREATE TABLE Professor (
                id_professor SERIAL NOT NULL,
                id_usuario INTEGER NOT NULL,
                CONSTRAINT id_professor PRIMARY KEY (id_professor)
);


CREATE TABLE Grupo (
                id_grupo SERIAL NOT NULL,
                nome VARCHAR NOT NULL,
                id_professor INTEGER NOT NULL,
                CONSTRAINT id_grupo PRIMARY KEY (id_grupo)
);

-- Relacao Grupo Usuarios
CREATE TABLE Participa (
                id_grupo INTEGER NOT NULL,
                id_usuario INTEGER NOT NULL,
                CONSTRAINT _id_grupo__id_usuario_ PRIMARY KEY (id_grupo, id_usuario)
);


CREATE TABLE Area (
                id_area SERIAL NOT NULL,
                nome VARCHAR NOT NULL,
                CONSTRAINT id_area PRIMARY KEY (id_area)
);


CREATE TABLE Editora (
                id_editora SERIAL NOT NULL,
                nome_editora VARCHAR NOT NULL,
                CONSTRAINT id_editora PRIMARY KEY (id_editora)
);


CREATE TABLE Livro (
                id_livro SERIAL NOT NULL,
                id_editora INTEGER NOT NULL,
                titulo VARCHAR NOT NULL,
                num_edicao INTEGER NOT NULL,
                ano INTEGER NOT NULL,
                link_capa VARCHAR NULL,
                link_down VARCHAR NULL,
                observacao VARCHAR NULL,
                CONSTRAINT id_livro PRIMARY KEY (id_livro)
);

-- Relacao Usuario TEM livros
CREATE TABLE Usuario_Livro (
                id_usuario INTEGER NOT NULL,
                id_livro INTEGER NOT NULL,
                CONSTRAINT _id_usuario__id_livro_ PRIMARY KEY (id_usuario, id_livro)
);

-- Relacao livros TEM area
CREATE TABLE Tem (
                id_area INTEGER NOT NULL,
                id_livro INTEGER NOT NULL,
                CONSTRAINT _id_livro_id_area_ PRIMARY KEY (id_area, id_livro)
);

-- Relacao Autor Escreve Livro
CREATE TABLE Escreve (
                id_livro INTEGER NOT NULL,
                id_autor INTEGER NOT NULL,
                CONSTRAINT _id_autor__id_livro_ PRIMARY KEY (id_livro, id_autor)
);

-- Tabela que ira guardar o backup dos usuarios
CREATE TABLE Backup_Usuario (
                id_usuario SERIAL NOT NULL,
                nome VARCHAR NOT NULL,
                senha VARCHAR NOT NULL,
                email VARCHAR NOT NULL,                
                link_foto VARCHAR NULL,
                curso VARCHAR NULL
);

ALTER TABLE Escreve ADD CONSTRAINT autor_escreve_fk
FOREIGN KEY (id_autor)
REFERENCES Autor (id_autor)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Professor ADD CONSTRAINT usuario_professor_fk
FOREIGN KEY (id_usuario)
REFERENCES Usuario (id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Participa ADD CONSTRAINT usuario_participa_fk
FOREIGN KEY (id_usuario)
REFERENCES Usuario (id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Usuario_Livro ADD CONSTRAINT usuario_usuario_livro_fk
FOREIGN KEY (id_usuario)
REFERENCES Usuario (id_usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Grupo ADD CONSTRAINT professor_grupo_fk
FOREIGN KEY (id_professor)
REFERENCES Professor (id_professor)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Participa ADD CONSTRAINT grupo_participa_fk
FOREIGN KEY (id_grupo)
REFERENCES Grupo (id_grupo)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Tem ADD CONSTRAINT area_tem_fk
FOREIGN KEY (id_area)
REFERENCES Area (id_area)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Livro ADD CONSTRAINT editora_livro_fk
FOREIGN KEY (id_editora)
REFERENCES Editora (id_editora)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Escreve ADD CONSTRAINT livro_escreve_fk
FOREIGN KEY (id_livro)
REFERENCES Livro (id_livro)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Tem ADD CONSTRAINT livro_tem_fk
FOREIGN KEY (id_livro)
REFERENCES Livro (id_livro)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Usuario_Livro ADD CONSTRAINT livro_usuario_livro_fk
FOREIGN KEY (id_livro)
REFERENCES Livro (id_livro)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
