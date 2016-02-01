package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IUsuarioDAO;
import entity.Usuario;
import factory.ConnectionFactory;

public class UsuarioJDBCDAO implements IUsuarioDAO {

	private Connection connection = null;
	
	public void cadastrarUsuario(Usuario usuario) {
		try {
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO USUARIO ("
					+ "nome,"
					+ "senha,"
					+ "email,"
					+ "link_foto,"
					+ "curso"
					+ ") VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getEmail());
			preparedStatement.setString(4, usuario.getLink_foto());
			preparedStatement.setString(5, usuario.getCurso());
			
						
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}		
	}

	public void removerUsuarioPorID(int idUsuario) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM USUARIO WHERE id_usuario  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM USUARIO";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = map(resultSet);
				listaUsuario.add(usuario);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return listaUsuario;
	}

	private Usuario map(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id_usuario"));
		usuario.setNome(rs.getString("nome"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setEmail(rs.getString("email"));
		usuario.setLink_foto(rs.getString("link_foto"));
		return usuario;
	}
	
	public List<Usuario> procurarPorNome(String nomeUsuario) {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM USUARIO WHERE USUARIO.nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeUsuario + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = map(resultSet);
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return listaUsuarios;

	}

	public Usuario procurarPorId(int idUsuario) {
		Usuario autor = new Usuario();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM USUARIO WHERE USUARIO.id_usuario = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUsuario);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				autor = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return autor;

	}

}
