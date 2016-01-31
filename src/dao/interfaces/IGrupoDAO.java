package dao.interfaces;

import java.util.List;

import entity.Grupo;



public interface IGrupoDAO {

	public void cadastrarGrupo(Grupo grupo);
	
	public void removerGrupoPorID(int idGrupo);

	public List<Grupo> listarTodosGrupos();
	
	public List<Grupo> procurarPorNome(String nomeEditora);
	
	public Grupo procurarPorId(int idGrupo);

}
