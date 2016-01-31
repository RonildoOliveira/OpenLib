package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IR_LivrosAreaDAO;
import entity.AreaLivro;
import entity.Livro;
import entity.relations.Livros_Areas;
import factory.ConnectionFactory;

public class R_Livros_AreasJDBCDAO implements IR_LivrosAreaDAO {

	private Connection connection = null;
	
	public List<Livros_Areas> listarLivrosAreas() {
		List<Livros_Areas> listaLivrosAreas = new ArrayList<Livros_Areas>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVROS_AREAS";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Livros_Areas livros_Areas = map(resultSet);
				listaLivrosAreas.add(livros_Areas);
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
		return listaLivrosAreas;
	}

	private Livros_Areas map(ResultSet rs) throws SQLException {
		Livros_Areas livrosAreas = new Livros_Areas();
		livrosAreas.setId(rs.getInt("id"));
		livrosAreas.setAreaLivro(rs.getInt("id_area"));
		livrosAreas.setLivro(rs.getInt("id_livro"));
		return livrosAreas;
	}
	
	public List<Livro> procurarLivrosPorNomeArea(String nomeArea) {
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVRO "
					+ "INNER JOIN LIVROS_AREAS ON LIVRO.id = LIVROS_AREAS.id_livro "
					+ "INNER JOIN AREA_LIVRO ON LIVROS_AREAS.id_area = AREA_LIVRO.id "
					+ "AND AREA_LIVRO.nome ILIKE ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeArea + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				LivroJDBCDAO ljdbc = new LivroJDBCDAO();
				
				Livro livro = ljdbc.mapLivro(resultSet);
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

	public List<AreaLivro> procurarAreaPorNomeLivro(String nomeLivro) {
		List<AreaLivro> listaAreaLivro = new ArrayList<AreaLivro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * from AREA_LIVRO "
					+ "INNER JOIN LIVROS_AREAS ON LIVROS_AREAS.id_area = AREA_LIVRO.id "
					+ "INNER JOIN LIVRO ON LIVRO.id = LIVROS_AREAS.id_livro "
					+ "AND LIVRO.titulo ILIKE ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeLivro + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				AreaLivroJDBCDAO arjdbc = new AreaLivroJDBCDAO();
				
				AreaLivro areaLivro = arjdbc.mapArea(resultSet);
				listaAreaLivro.add(areaLivro);
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
		return listaAreaLivro;

	}	

}
