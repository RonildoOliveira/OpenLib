package dao.interfaces;

import java.util.List;

import entity.Area;
import entity.Livro;
import entity.relations.Livros_Areas;



public interface IR_LivrosAreaDAO {

	public List<Livros_Areas> listarLivrosAreas();
	
	public List<Livro> procurarLivrosPorNomeArea(String nomeArea);
	
	public List<Area> procurarAreaPorNomeLivro(String nomeLivro);

}
