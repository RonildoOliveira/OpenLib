package dao.interfaces;

import java.util.List;

import entity.AreaLivro;



public interface IAreaLivroDAO {

public void cadastrarArea(AreaLivro areaLivro);
	
	public void removerAreaPorID(int idArea);

	public List<AreaLivro> listarTodasAreas();
	
	public List<AreaLivro> procurarPorNome(String nomeArea);
	
	public AreaLivro procurarPorId(int idArea);

}
