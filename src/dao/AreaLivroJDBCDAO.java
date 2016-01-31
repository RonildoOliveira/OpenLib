package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IAreaLivroDAO;
import entity.AreaLivro;
import factory.ConnectionFactory;

public class AreaLivroJDBCDAO implements IAreaLivroDAO {

	private Connection connection = null;
	
	@Override
	public void cadastrarArea(AreaLivro areaLivro) {
		try {
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO AREA_LIVRO ("
					+ "nome"
					+ ") VALUES (?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, areaLivro.getNome());
						
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
	public void removerAreaPorID(int idArea) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM AREA_LIVRO WHERE id  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idArea);
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
	public List<AreaLivro> listarTodasAreas() {
		List<AreaLivro> listaAreas = new ArrayList<AreaLivro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA_LIVRO";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				AreaLivro areaLivro = mapArea(resultSet);
				listaAreas.add(areaLivro);
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
		return listaAreas;
	}

	public AreaLivro mapArea(ResultSet rs) throws SQLException {
		AreaLivro areaLivro = new AreaLivro();
		areaLivro.setId(rs.getInt("id"));
		areaLivro.setNome(rs.getString("nome"));
		return areaLivro;
	}
	
	@Override
	public List<AreaLivro> procurarPorNome(String nomeArea) {
		List<AreaLivro> listaAreas = new ArrayList<AreaLivro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA_LIVRO WHERE nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeArea + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				AreaLivro area = mapArea(resultSet);
				listaAreas.add(area);
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
		return listaAreas;
	}

	@Override
	public AreaLivro procurarPorId(int idArea) {
		AreaLivro  areaLivro = new AreaLivro();		
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA_LIVRO WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idArea);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				areaLivro = mapArea(resultSet);
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
		return areaLivro;
	}

}
