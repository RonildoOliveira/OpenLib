package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IProfessorDAO;
import entity.Professor;
import factory.ConnectionFactory;

public class ProfessorJDBCDAO implements IProfessorDAO {

	private Connection connection = null;
	
	public void cadastrarProfessor(Professor professor) {
		try {
			
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO PROFESSOR ("
					+ "id_usuario"
					+ ") VALUES (?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setInt(1, professor.getId_usuario());
						
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

	public void removerProfessorPorID(int idProfessor) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM PROFESSOR WHERE id_professor  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idProfessor);
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

	public List<Professor> listarTodosProfessores() {
		List<Professor> listaProfessores = new ArrayList<Professor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PROFESSOR";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
								
			while (resultSet.next()) {
				Professor professor = map(resultSet);
				listaProfessores.add(professor);
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
		return listaProfessores;
	}

	private Professor map(ResultSet rs) throws SQLException {
		Professor professor = new Professor();
		UsuarioJDBCDAO ujdbc = new UsuarioJDBCDAO();
		professor.setId(rs.getInt("id_professor"));
		professor.setId_usuario(rs.getInt("id_usuario"));
		professor.setNome(ujdbc.procurarPorId(rs.getInt("id_usuario")).getNome());
		return professor;
	}
	
	public List<Professor> procurarPorNome(String nomeProfessor) {
		List<Professor> listaProfessores = new ArrayList<Professor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM USUARIO, PROFESSOR WHERE USUARIO.nome ILIKE ? "
					+ "AND USUARIO.id_usuario = PROFESSOR.id_usuario";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeProfessor + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Professor professor = map(resultSet);
				listaProfessores.add(professor);
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
		return listaProfessores;

	}

	public Professor procurarPorId(int idProfessor) {
		Professor autor = new Professor();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PROFESSOR WHERE PROFESSOR.id_professor = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idProfessor);
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
