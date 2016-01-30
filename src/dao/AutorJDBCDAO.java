package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IAutorDAO;
import entity.Autor;
import entity.Livro;
import factory.ConnectionFactory;

public class AutorJDBCDAO implements IAutorDAO {

	private Connection connection = null;
	
	@Override
	public void cadastrarAutor(Autor autor) {
		String insert_sql;
		try {
			connection = ConnectionFactory.getConnection();
			if (autor.getId() != 0) {
				insert_sql = "INSERT INTO AUTOR (nome,id_usuario) "
						+ "SELECT USUARIO.nome, USUARIO.id FROM USUARIO WHERE id = 5";

			}else{
				insert_sql = "INSERT INTO AUTOR ("
						+ "nome,"
						+ "id_usuario"
						+ ") VALUES (?, ?)";
			}
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, autor.getNome());
			preparedStatement.setInt(2, autor.getId_usuario());
						
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

	@Override
	public void removerAutorPorID(int idAutor) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM AUTOR WHERE id  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAutor);
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

	@Override
	public List<Autor> listarTodosAutores() {
		List<Autor> listaAutores = new ArrayList<Autor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVRO";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Autor autor = map(resultSet);
				listaAutores.add(autor);
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
		return listaAutores;
	}

	private Autor map(ResultSet rs) throws SQLException {
		Autor autor = new Autor();
		autor.setId(rs.getInt("id"));
		autor.setNome(rs.getString("nome"));
		autor.setId_usuario(rs.getInt("id_usuario"));
		return autor;
	}
	@Override
	public List<Autor> procurarPorNome(String nomeAutor) {
		List<Autor> listaAutores = new ArrayList<Autor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AUTOR WHERE nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeAutor + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Autor autor = map(resultSet);
				listaAutores.add(autor);
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
		return listaAutores;

	}

	@Override
	public Autor procurarPorId(int idAutor) {
		Autor autor = new Autor();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AUTOR WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAutor);
			
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
