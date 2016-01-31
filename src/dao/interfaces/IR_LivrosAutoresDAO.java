package dao.interfaces;

import java.util.List;

import entity.Autor;
import entity.Livro;
import entity.relations.Livros_Autores;



public interface IR_LivrosAutoresDAO {

	public List<Livros_Autores> listarLivrosAutores();
	
	public List<Livro> procurarLivrosPorNomeAutor(String nomeAutor);
	
	public List<Autor> procurarAutoresPorNomeLivro(String nomeLivro);

}
