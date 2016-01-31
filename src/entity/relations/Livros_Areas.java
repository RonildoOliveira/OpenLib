package entity.relations;

import dao.AreaLivroJDBCDAO;
import dao.LivroJDBCDAO;
import entity.AreaLivro;
import entity.Livro;

public class Livros_Areas {
	
	private AreaLivro areaLivro;
	private Livro livro;
	private int id;
	
	public Livros_Areas() {

	}
	
	public Livros_Areas(Livro livro, AreaLivro areaLivro) {
		this.livro = livro;
		this.areaLivro = areaLivro;
	}

	public AreaLivro getAreaLivro() {
		return areaLivro;
	}

	public void setAreaLivro(int idArea) {
		AreaLivroJDBCDAO arjdbc = new AreaLivroJDBCDAO();
		this.areaLivro = arjdbc.procurarPorId(idArea);
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
