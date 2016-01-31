package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IR_LivrosAutoresDAO;
import entity.Autor;
import entity.Livro;
import entity.relations.Livros_Autores;
import factory.ConnectionFactory;

public class R_Livros_AutoresJDBCDAO implements IR_LivrosAutoresDAO {

	private Connection connection = null;
	
	public List<Livros_Autores> listarLivrosAutores() {
		List<Livros_Autores> listaLivrosAutores = new ArrayList<Livros_Autores>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVROS_AUTORES";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Livros_Autores usuario = map(resultSet);
				listaLivrosAutores.add(usuario);
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
		return listaLivrosAutores;
	}

	private Livros_Autores map(ResultSet rs) throws SQLException {
		Livros_Autores livrosAutores = new Livros_Autores();
		livrosAutores.setId(rs.getInt("id"));
		livrosAutores.setAutor(rs.getInt("id_autor"));
		livrosAutores.setLivro(rs.getInt("id_livro"));
		return livrosAutores;
	}
	
	public List<Livro> procurarLivrosPorNomeAutor(String nomeAutor) {
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * from LIVRO "
					+ "INNER JOIN LIVROS_AUTORES ON LIVRO.id = LIVROS_AUTORES.id_livro "
					+ "INNER JOIN AUTOR ON LIVROS_AUTORES.id_autor = AUTOR.id "
					+ "AND AUTOR.nome ILIKE ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeAutor + "%");
			
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

	public List<Autor> procurarAutoresPorNomeLivro(String nomeLivro) {
		List<Autor> listaAutores = new ArrayList<Autor>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * from AUTOR "
					+ "INNER JOIN LIVROS_AUTORES ON LIVROS_AUTORES.id_autor = AUTOR.id "
					+ "INNER JOIN LIVRO ON LIVRO.id = LIVROS_AUTORES.id_livro "
					+ "AND LIVRO.titulo ILIKE ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nomeLivro + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				AutorJDBCDAO atjdbc = new AutorJDBCDAO();
				
				Autor autor = atjdbc.mapAutor(resultSet);
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
	

}
