-- psql -U postgres -h localhost	
-- pass "postgres"


---
INSERT INTO AREA (nome) VALUES 
('Mobile'),			--1
('Matematica'), 	--2
('Web'),			--3
('Java'),			--4
('Algoritmos'),		--5
('UML'),			--6
('Banco de Dados'),	--7
('Redes de Comp.'),	--8
('Linux'),			--9
('Agile')			--10
;


INSERT INTO EDITORA (nome_editora) VALUES
('Novatec'),		--1
('Pearson'),		--2
('Érica'),			--3
('Bookman'),		--4
('Campus'),			--5
('HARBRA'),			--6
('LTC'),			--7
('Makron Books'),	--8
('Ciência Moderna'),--9
('Casa do Código'),	--10
('Mc Graw Hill')	--11
; 


INSERT INTO USUARIO (nome, senha, email, link_foto, curso) VALUES ('Ronildo','ron123','ronildo@mail','foto.jpg','Ciência da Computação'),
('Erivelton','eri123','erio@mail','foto.jpg','Engenharia de Software'),
('João Paulo','jp123','jp@mail','foto.jpg','Engenharia da Computação'),
('Lucas','lc123','lc@mail','foto.jpg','Design Digital'),
('Felipe','fp123','fp@mail','foto.jpg','Sistemas de Informação'),
('Heli','hl123','hl@mail','foto.jpg','Redes de Computadores'),
('Décio','dec123','decio@mail','foto.jpg','Ciência da Computação'),
('Marcos','mpaulo123','mpaulo@mail','foto.jpg','Engenharia de Software'),
('Matheus','mt123','matheus@mail','foto.jpg','Engenharia da Computação'),
('Daiane','dai123','daiane@mail','foto.jpg','Ciência da Computação'),
--prof
('Regis Pires', 'reg456','regis@mail','foto.jpg',NULL),	--1
('Criston', 'cri456','cris@mail','foto.jpg',NULL),		--2
('Marcos', 'mar456','marca1@mail','foto.jpg',NULL),		--3
('David', 'dav456','sena@mail','foto.jpg',NULL),		--4
('Lucas', 'luke456','skywalker@mail','foto.jpg',NULL),	--5
('Arthur', 'art456','araras@mail','foto.jpg',NULL),		--6
('Elder', 'elder456','chiquito@mail','foto.jpg',NULL),	--7
('Filho', 'son456','carlos_son@mail','foto.jpg',NULL),	--8
('Fabio', 'fabio456','fabim@mail','foto.jpg',NULL),		--9
('Jefferson', 'jeff456','1_porcento@mail','foto.jpg',NULL)	--10
;

INSERT INTO AUTOR (nome_autor) VALUES
('Ricardo R. Lecheta'),			--1		
('Keneth Rosen'),				--2		
('Eduardo Bezerra'),			--3
('Elmasri'),					--4
('Ramez'),						--5
('Navathe'),					--6
('Thomas H. Cormen'),			--7
('Charles E. Leiserson'),		--8
('Ronald L. Rivest'),			--9
('Clifford Stein'),				--10
('Jayme Luiz Szwarcfiter'),		--11
('Louis Leithold'),				--12
('Sílvio César Roxo Giavaroto'),--13
('Gerson Raimundo dos Santos'),	--14
('Victorine Viviane Mizrahi'),	--15
('Daniel Wildt'),				--16
('Dionatan Moura'),				--17
('Guilherme Lacerda'),			--18
('Rafael Helm'),				--19
('Lucas Mazza'),				--20
('Alexandre Altair'),			--21
('Décio Heinzelmann'),			--22
('Paulo Sérgio Marin') 			--23
;

INSERT INTO LIVRO (titulo, ano, id_editora, num_edicao, link_capa, link_down, observacao) VALUES
('Google Android', 2015,1,5,'capa.png','livro.pdf','livro'), --1
('Java Para Web', 2015, 1,3,'capa.png','livro.pdf','livro'),
('Matemática Discreta', 2009, 11,6,'capa.png','livro.pdf','livro'), --3
('Algoritmos', 2012, 5,3,'capa.png','livro.pdf','livro'),
('Análise e Projeto de Sistemas', 2002, 5,2,'capa.png','livro.pdf','livro'), --5
('Sistemas de Banco de Dados', 2011, 2,6,'capa.png','livro.pdf','livro'),
('Estrutura de Dados e Seus Algoritmos', 1994, 7,1,'capa.png','livro.pdf','livro'), --7
('O Calculo Com Geometria Analitica', 1982, 1,3,'capa.png','livro.pdf','livro'), 
('Cabeamento Estruturado', 2014, 3,2,'capa.png','livro.pdf','livro'), --9
('Backtrack Linux ', 2013, 9,1,'capa.png','livro.pdf','livro'),
('eXtreme Programming', 2015, 10,1,'capa.png','livro.pdf','livro'), --11
('HTML5 e CSS3', 2013, 10,1,'capa.png','livro.pdf','livro'),
('Treinamento em Linguagem C++', 2009, 2,2,'capa.png','livro.pdf','livro') -- 13
;

INSERT INTO ESCREVE (id_autor, id_livro) VALUES
(1,1),
(2,3),
(3,5),
(4,6),
(5,6),
(6,6),
(7,4),
(8,4),
(9,4),
(10,4),
(11,7),
(12,8),
(13,10),
(14,10),
(15,13),
(16,11),
(17,11),
(18,11),
(19,11),
(20,12),
(21,2),
(22,2),
(23,9)
;

INSERT INTO PROFESSOR (id_usuario) VALUES
(11),	--1
(12),	--2
(13),	--3
(14),	--4
(15),	--5
(16),	--6
(17),	--7
(18),	--8
(19),	--9
(20)	--10
;

INSERT INTO GRUPO (nome, id_professor) VALUES
('PAA',2),	--1
('CC',6),	--2
('SI',5),	--3
('RC',7),	--4
('ES',3),	--5
('EC',8),	--6
('DD',10),	--7
('FUP',4),	--8
('ED',9),	--9
('NPI',1)	--10
;

--um grupo so existe se um professor tomar conta
INSERT INTO PARTICIPA (id_usuario, id_grupo) VALUES
(1,1), -- grupo PAA
(2,1),
(9,1),
(1,2), -- grupo CC
(6,2),
(8,1),
(7,3), -- grupo SI
(5,3),
(9,3),
(2,4), -- grupo RC
(8,4),
(1,4)
;

INSERT INTO USUARIO_LIVRO (id_usuario, id_livro) VALUES
(1,3),
(5,4),
(7,3),
(6,2),
(9,12),
(9,3),
(7,7),
(16,8),
(1,8),
(3,6),
(2,1),
(3,1),
(5,5),
(5,9),
(8,6),
(4,7),
(3,8),
(12,3),
(14,4),
(3,3),
(4,9)
;

-- Relacao livros TEM area
INSERT INTO TEM (id_area, id_livro) VALUES 
(1,1),
(3,2),
(2,3),
(5,4),
(10,5),
(7,6),
(5,7),
(2,8),
(8,9),
(9,10),
(10,11),
(3,12),
(5,13)
;
