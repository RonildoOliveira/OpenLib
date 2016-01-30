package dao.interfaces;

import java.util.List;

import entity.Usuario;


public interface IUsuarioDAO {

	public void cadastrarUsuario(Usuario usuario);
	
	public void removerUsuarioPorID(int idUsuario);

	public List<Usuario> listarTodosUsuarios();
	
	public List<Usuario> procurarPorNome(String nomeUsuario);
	
	public Usuario procurarPorId(int idUsuario);

}
