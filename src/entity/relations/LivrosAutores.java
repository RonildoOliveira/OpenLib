package entity.relations;

import dao.AutorJDBCDAO;
import dao.LivroJDBCDAO;
import entity.Autor;
import entity.Livro;

public class LivrosAutores {
	private Autor autor;
	private Livro livro;
	private int id;
	
	public LivrosAutores() {

	}
	
	public LivrosAutores(Livro livro, Autor autor) {
		this.livro = livro;
		this.autor = autor;
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(int idAutor) {
		AutorJDBCDAO ajdbc = new AutorJDBCDAO();
		this.autor = ajdbc.procurarPorId(idAutor);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(int idLivro) {
		LivroJDBCDAO ljdbc = new LivroJDBCDAO();
		this.livro = ljdbc.procurarPorId(idLivro);		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
