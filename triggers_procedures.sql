-- Tabela que salavara usuarios

CREATE TABLE Backup_Usuario (
                id_usuario SERIAL NOT NULL,
                nome VARCHAR NOT NULL,
                senha VARCHAR NOT NULL,
                email VARCHAR NOT NULL,                
                link_foto VARCHAR NULL,
                curso VARCHAR NULL
);

-- Stored Procedure (Funcao de Backup)
CREATE OR REPLACE FUNCTION backupuser()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO Backup_Usuario VALUES
(old.id_usuario, old.nome, old.senha, 
old.email, old.link_foto, old.curso);

RETURN NULL;

END;
$$ LANGUAGE plpgsql;

-- Stored Procedure (Avisa)
CREATE OR REPLACE FUNCTION avisa_area()
RETURNS VARCHAR(200) AS
$func$
BEGIN
   RETURN (
   SELECT AREA.nome FROM AREA 
   WHERE AREA.nome ILIKE '%Artificial%'
   );
END
$func$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER delete_usuario
AFTER DELETE
ON Usuario
FOR EACH ROW
EXECUTE PROCEDURE backupuser();


