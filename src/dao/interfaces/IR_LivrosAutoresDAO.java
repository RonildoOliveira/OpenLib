package dao.interfaces;

import java.util.List;

import entity.Autor;
import entity.Livro;
import entity.relations.LivrosAutores;



public interface IR_LivrosAutoresDAO {

	public List<LivrosAutores> listarLivrosAutores();
	
	public List<Livro> procurarLivrosPorNomeAutor(String nomeAutor);
	
	public List<Autor> procurarAutoresPorNomeLivro(String nomeLivro);

}
