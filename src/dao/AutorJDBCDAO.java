package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IAutorDAO;
import entity.Autor;
import factory.ConnectionFactory;

public class AutorJDBCDAO implements IAutorDAO {

	private Connection connection = null;
	
	@Override
	public void cadastrarAutor(Autor autor) {
		String insert_sql;
		try {
			connection = ConnectionFactory.getConnection();
			insert_sql = "INSERT INTO AUTOR ("
					+ "nome_autor"
					+ ") VALUES (?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, autor.getNome());
						
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
			String sql = "DELETE FROM AUTOR WHERE id_autor  = ?";
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
			String sql = "SELECT * FROM AUTOR";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Autor autor = mapAutor(resultSet);
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

	public Autor mapAutor(ResultSet rs) throws SQLException {
		Autor autor = new Autor();
		autor.setId(rs.getInt("id_autor"));
		autor.setNome(rs.getString("nome_autor"));
		return autor;
	}
	@Override
	public List<Autor> procurarPorNome(String nomeAutor) {
		List<Autor> listaAutores = new ArrayList<Autor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AUTOR WHERE nome_autor ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeAutor + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Autor autor = mapAutor(resultSet);
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
			String sql = "SELECT * FROM AUTOR WHERE id_autor = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAutor);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				autor = mapAutor(resultSet);
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
