package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IAreaLivroDAO;
import entity.Area;
import factory.ConnectionFactory;

public class AreaJDBCDAO implements IAreaLivroDAO {

	private Connection connection = null;
	
	@Override
	public void cadastrarArea(Area areaLivro) {
		try {
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO AREA ("
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
			String sql = "DELETE FROM AREA WHERE id_area  = ?";
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
	public List<Area> listarTodasAreas() {
		List<Area> listaAreas = new ArrayList<Area>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Area areaLivro = mapArea(resultSet);
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

	public Area mapArea(ResultSet rs) throws SQLException {
		Area areaLivro = new Area();
		areaLivro.setId(rs.getInt("id_area"));
		areaLivro.setNome(rs.getString("nome"));
		return areaLivro;
	}
	
	@Override
	public List<Area> procurarPorNome(String nomeArea) {
		List<Area> listaAreas = new ArrayList<Area>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA WHERE nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeArea + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Area area = mapArea(resultSet);
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
	public Area procurarPorId(int idArea) {
		Area  areaLivro = new Area();		
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM AREA WHERE id_area = ?";
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
