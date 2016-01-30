package dao.interfaces;

import java.util.List;

import entity.Editora;



public interface IEditoraDAO {

	public void cadastrarEditora(Editora editora);
	
	public void removerEditoraPorID(int idEditora);

	public List<Editora> listarTodasEditoras();
	
	public List<Editora> procurarPorNome(String nomeEditora);
	
	public Editora procurarPorId(int idEditora);

}
