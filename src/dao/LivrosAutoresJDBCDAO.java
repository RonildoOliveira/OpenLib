package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IR_LivrosAutoresDAO;
import dao.interfaces.IUsuarioDAO;
import entity.Autor;
import entity.Livro;
import entity.relations.LivrosAutores;
import factory.ConnectionFactory;

public class LivrosAutoresJDBCDAO implements IR_LivrosAutoresDAO {

	private Connection connection = null;
	
	public List<LivrosAutores> listarLivrosAutores() {
		List<LivrosAutores> listaLivrosAutores = new ArrayList<LivrosAutores>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM LIVROS_AUTORES";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				LivrosAutores usuario = map(resultSet);
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

	private LivrosAutores map(ResultSet rs) throws SQLException {
		LivrosAutores livrosAutores = new LivrosAutores();
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
