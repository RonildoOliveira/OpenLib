package entity.relations;

import dao.AreaJDBCDAO;
import dao.LivroJDBCDAO;
import entity.Area;
import entity.Livro;

public class Livros_Areas {
	
	private Area areaLivro;
	private Livro livro;
	private int id;
	
	public Livros_Areas() {

	}
	
	public Livros_Areas(Livro livro, Area areaLivro) {
		this.livro = livro;
		this.areaLivro = areaLivro;
	}

	public Area getAreaLivro() {
		return areaLivro;
	}

	public void setAreaLivro(int idArea) {
		AreaJDBCDAO arjdbc = new AreaJDBCDAO();
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
