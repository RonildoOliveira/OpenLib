package dao.interfaces;

import java.util.List;

import entity.Autor;

public interface IAutorDAO {

	public void cadastrarAutor(Autor autor);
	
	public void removerAutorPorID(int idAutor);

	public List<Autor> listarTodosAutores();
	
	public List<Autor> procurarPorNome(String nomeAutor);
	
	public Autor procurarPorId(int idAutor);
	
}
