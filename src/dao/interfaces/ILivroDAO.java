package dao.interfaces;

import java.util.List;

import entity.Livro;

public interface ILivroDAO {
	
	public void cadastrarLivro(Livro livro);
	
	public void removerLivroPorID(int idLivro);

	public List<Livro> listarTodosLivros();
	
	public List<Livro> procurarPorTitulo(String tituloLivro);
	
	public Livro procurarPorId(int idLivro);
		
}
