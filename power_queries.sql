-- [INSERÇOES E PERIPECIAS]
-- inserir usuario regis como autor (num 24)
INSERT INTO AUTOR (nome_autor) VALUES ('Regis Pires');

-- inserir livro 7 passos (livro 14)
INSERT INTO LIVRO (titulo, ano, id_editora, num_edicao, link_capa, link_down, observacao) VALUES
('7 Passos Para Uma Boa Modelagem',2016,7,1,'livro.jpg','livro.pdf','7 Passos para a felicidade');

--vincular livro ao regis
INSERT INTO ESCREVE (id_autor, id_livro) VALUES (24,14);

-- vincluar livro a area
INSERT INTO TEM (id_area, id_livro) VALUES (7,14);

-- vincular o livro do regis a mim
INSERT INTO USUARIO_LIVRO (id_usuario, id_livro) VALUES (1,14);

----

-- lista usuarios e seus grupos
SELECT USUARIO.nome, GRUPO.nome FROM USUARIO INNER JOIN
PARTICIPA ON PARTICIPA.id_usuario = USUARIO.id_usuario INNER JOIN
GRUPO ON GRUPO.id_grupo = PARTICIPA.id_grupo ORDER BY GRUPO.nome;

-- lista autores e livros
SELECT AUTOR.nome_autor, LIVRO.titulo from AUTOR INNER JOIN
ESCREVE ON ESCREVE.id_autor = AUTOR.id_autor INNER JOIN
LIVRO ON LIVRO.id_livro = ESCREVE.id_livro ORDER BY LIVRO.titulo;

-- lista usuarios e seus livros
SELECT USUARIO.nome, LIVRO.titulo from USUARIO INNER JOIN
USUARIO_LIVRO ON USUARIO_LIVRO.id_usuario = USUARIO.id_usuario INNER JOIN
LIVRO ON LIVRO.id_livro = USUARIO_LIVRO.id_livro ORDER BY USUARIO.nome;

-------POWER CONSULTA lista usuarios e seus livros
SELECT USUARIO.nome, LIVRO.titulo, EDITORA.nome_editora from USUARIO INNER JOIN
USUARIO_LIVRO ON USUARIO_LIVRO.id_usuario = USUARIO.id_usuario INNER JOIN
LIVRO ON LIVRO.id_livro = USUARIO_LIVRO.id_livro INNER JOIN
EDITORA ON EDITORA.id_editora = LIVRO.id_editora
ORDER BY USUARIO.nome;

-------POWER CONSULTA lista editoras ordenadas pela quantidade de autores
SELECT EDITORA.nome_editora, COUNT(AUTOR.id_autor) AS num_autores
FROM EDITORA
INNER JOIN LIVRO ON LIVRO.id_editora = EDITORA.id_editora
INNER JOIN ESCREVE ON ESCREVE.id_livro = LIVRO.id_livro
INNER JOIN AUTOR ON AUTOR.id_autor = ESCREVE.id_autor
AND ESCREVE.id_livro = LIVRO.id_livro
GROUP BY EDITORA.nome_editora HAVING COUNT(AUTOR.id_autor) > 0
ORDER BY COUNT(AUTOR.id_autor) DESC;

-------POWER CONSULTA lista todos os usuarios que possuem nome de autor
SELECT * FROM usuario WHERE nome IN 
(SELECT nome_autor FROM AUTOR); 

-------POWER CONSULTA lista o numero de professores e nao professores
SELECT COUNT(PROFESSOR.id_professor) FROM PROFESSOR
UNION ALL
SELECT COUNT(USUARIO.id_usuario) FROM USUARIO 
WHERE USUARIO.id_usuario NOT IN
(SELECT PROFESSOR.id_usuario FROM PROFESSOR);

-------POWER CONSULTA lista todos os usuarios que possuem nome de autor
SELECT * FROM usuario WHERE nome IN 
(SELECT nome_autor FROM AUTOR); 

-------POWER CONSULTA lista todos os usuarios que possuem nome de autor
-- com EXISTS
SELECT * FROM USUARIO WHERE EXISTS
(SELECT AUTOR.nome_autor FROM AUTOR WHERE USUARIO.nome = AUTOR.nome_autor); 


-- lista autores e livros e area
-- VIEW IMPORTANTE
CREATE VIEW listar_autores_livros AS
SELECT  LIVRO.titulo, AUTOR.nome_autor AS autor, AREA.nome AS area from AUTOR INNER JOIN
ESCREVE ON ESCREVE.id_autor = AUTOR.id_autor FULL JOIN
LIVRO ON LIVRO.id_livro = ESCREVE.id_livro FULL JOIN
TEM ON TEM.id_livro = LIVRO.id_livro INNER JOIN
AREA ON AREA.id_area = TEM.id_area
ORDER BY LIVRO.titulo;

-- lista livros pelo nome do autor *
SELECT LIVRO.titulo from LIVRO
INNER JOIN ESCREVE ON LIVRO.id_livro = ESCREVE.id_livro
INNER JOIN AUTOR ON ESCREVE.id_autor = AUTOR.id_autor AND AUTOR.nome_autor ILIKE '%Th%';


-- lista autores pelo nome do livro *
SELECT AUTOR.nome_autor from AUTOR 
INNER JOIN ESCREVE ON ESCREVE.id_autor = AUTOR.id_autor
INNER JOIN LIVRO ON LIVRO.id_livro = ESCREVE.id_livro AND LIVRO.titulo ILIKE '%Es%';

--- EXECUCOES ECLIPSE

-- procurar professor por nome
SELECT * FROM USUARIO, PROFESSOR WHERE USUARIO.nome ILIKE '%%' 
AND USUARIO.id_usuario = PROFESSOR.id_usuario;

--procurarLivrosPorNomeArea
SELECT * FROM LIVRO 
INNER JOIN TEM ON LIVRO.id_livro = TEM.id_livro
INNER JOIN AREA ON TEM.id_area = AREA.id_area
AND AREA.nome ILIKE '%Web%';

-- procurarAreaPorNomeLivro
SELECT * from AREA INNER JOIN TEM ON TEM.id_area = AREA.id_area
INNER JOIN LIVRO ON LIVRO.id_livro = TEM.id_livro 
AND LIVRO.titulo ILIKE  '%al%';

--procurarLivrosPorNomeAutor
SELECT * from LIVRO INNER JOIN ESCREVE ON LIVRO.id_livro = ESCREVE.id_livro
INNER JOIN AUTOR ON ESCREVE.id_autor = AUTOR.id_autor 
AND AUTOR.nome_autor ILIKE '%gis%';

--procurarAutoresPorNomeLivro
SELECT * from AUTOR INNER JOIN ESCREVE ON ESCREVE.id_autor = AUTOR.id_autor
INNER JOIN LIVRO ON LIVRO.id_livro = ESCREVE.id_livro 
AND LIVRO.titulo ILIKE '%Pa%';