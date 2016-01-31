package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IEditoraDAO;
import entity.Editora;
import factory.ConnectionFactory;

public class EditoraJDBCDAO implements IEditoraDAO {

	private Connection connection = null;
	
	public void cadastrarEditora(Editora editora) {
		try {
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO EDITORA ("
					+ "nome"
					+ ") VALUES (?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, editora.getNome());
						
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

	public void removerEditoraPorID(int idEditora) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM EDITORA WHERE id  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEditora);
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

	public List<Editora> listarTodasEditoras() {
		List<Editora> listaEditoras = new ArrayList<Editora>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM EDITORA";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Editora areaLivro = map(resultSet);
				listaEditoras.add(areaLivro);
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
		return listaEditoras;
	}

	private Editora map(ResultSet rs) throws SQLException {
		Editora editora = new Editora();
		editora.setId(rs.getInt("id"));
		editora.setNome(rs.getString("nome"));
		return editora;
	}
	
	public List<Editora> procurarPorNome(String nomeEditora) {
		List<Editora> listaEditoras = new ArrayList<Editora>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM EDITORA WHERE nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeEditora + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Editora area = map(resultSet);
				listaEditoras.add(area);
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
		return listaEditoras;
	}

	public Editora procurarPorId(int idEditora) {
		Editora editora = new Editora();		
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM EDITORA WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEditora);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				editora = map(resultSet);
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
		return editora;
	}

}
