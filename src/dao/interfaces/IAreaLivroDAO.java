package dao.interfaces;

import java.util.List;

import entity.Area;



public interface IAreaLivroDAO {

public void cadastrarArea(Area areaLivro);
	
	public void removerAreaPorID(int idArea);

	public List<Area> listarTodasAreas();
	
	public List<Area> procurarPorNome(String nomeArea);
	
	public Area procurarPorId(int idArea);

}
