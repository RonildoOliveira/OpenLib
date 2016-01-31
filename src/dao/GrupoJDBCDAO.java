package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IGrupoDAO;
import entity.Grupo;
import factory.ConnectionFactory;

public class GrupoJDBCDAO implements IGrupoDAO {

	private Connection connection = null;
	
	public void cadastrarGrupo(Grupo grupo) {
		try {			
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO GRUPO ("
					+ "nome,"
					+ "id_professor"
					+ ") VALUES (?,?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, grupo.getNome());
			preparedStatement.setInt(2, grupo.getProfessor().getId());
					
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

	public void removerGrupoPorID(int idGrupo) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM GRUPO WHERE id  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idGrupo);
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

	public List<Grupo> listarTodosGrupos() {
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM GRUPO";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Grupo grupo = map(resultSet);
				listaGrupos.add(grupo);
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
		return listaGrupos;
	}

	private Grupo map(ResultSet rs) throws SQLException {
		Grupo grupo = new Grupo();
		grupo.setId(rs.getInt("id"));
		grupo.setNome(rs.getString("nome"));
		grupo.setProfessor(rs.getInt("id_professor"));
		return grupo;
	}
	
	public List<Grupo> procurarPorNome(String nomeGrupo) {
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM GRUPO WHERE nome ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeGrupo + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Grupo grupo = map(resultSet);
				listaGrupos.add(grupo);
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
		return listaGrupos;
	}

	public Grupo procurarPorId(int idGrupo) {
		Grupo grupo = new Grupo();		
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM GRUPO WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idGrupo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				grupo = map(resultSet);
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
		return grupo;
	}

}
