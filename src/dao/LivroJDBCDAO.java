package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.ILivroDAO;
import entity.Livro;
import factory.ConnectionFactory;

public class LivroJDBCDAO implements ILivroDAO {
	
	private Connection connection = null;
	
	@Override
	public void cadastrarLivro(Livro livro) {
		
		try {
			connection = ConnectionFactory.getConnection();
			String insert_sql = "INSERT INTO LIVRO ("
					+ "titulo,"
					+ "ano,"
					+ "id_editora,"
					+ "num_edicao,"
					+ "link_capa,"
					+ "link_down,"
					+ "observacao"
					+ ") VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(insert_sql);
			
			preparedStatement.setString(1, livro.getTitulo());
			preparedStatement.setInt(2, livro.getAno());
			preparedStatement.setInt(3, livro.getId_editora());
			preparedStatement.setInt(4, livro.getNum_edicao());
			preparedStatement.setString(5, livro.getLink_capa());
			preparedStatement.setString(6, livro.getLink_down());
			preparedStatement.setString(7, livro.getObservacao());
			
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
	public void removerLivroPorID(int idLivro) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM LIVRO WHERE id_livro  = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idLivro);
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
	public List<Livro> listarTodosLivros() {

		List<Livro> listaLivros = new ArrayList<Livro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVRO";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Livro livro = mapLivro(resultSet);
				listaLivros.add(livro);
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
		return listaLivros;
	}

	public Livro mapLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setId(rs.getInt("id_livro"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setAno(rs.getInt("ano"));
		livro.setId_editora(rs.getInt("id_editora"));
		livro.setNum_edicao(rs.getInt("num_edicao"));
		livro.setLink_capa(rs.getString("link_capa"));
		livro.setLink_down(rs.getString("link_down"));
		livro.setObservacao(rs.getString("observacao"));
		return livro;
	}
	
	@Override
	public List<Livro> procurarPorTitulo(String tituloLivro) {
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVRO WHERE titulo ILIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + tituloLivro + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Livro livro = mapLivro(resultSet);
				listaLivros.add(livro);
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
		return listaLivros;
	}

	@Override
	public Livro procurarPorId(int idLivro) {
		Livro livro = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVRO WHERE id_livro = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idLivro);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				livro = mapLivro(resultSet);
			
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
		return livro;
	}
	
}
