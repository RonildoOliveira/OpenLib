package main;

import java.util.Iterator;

import dao.LivrosAutoresJDBCDAO;
import dao.UsuarioJDBCDAO;
import entity.Autor;
import entity.Livro;
import entity.Usuario;

public class Main {
	public static void main(String[] args){
			
		UsuarioJDBCDAO userjdbc = new UsuarioJDBCDAO();
		userjdbc.cadastrarUsuario(new Usuario("Flavio","jose","jose@mail","jose.png","Musica"));
		userjdbc.removerUsuarioPorID(7);
		
		LivrosAutoresJDBCDAO liat_jdbc = new LivrosAutoresJDBCDAO();
		
//		for (Autor at : liat_jdbc.procurarAutoresPorNomeLivro("Dados")) {
//			System.out.println(at.getNome());
//			
//		}
		
		for (Livro li : liat_jdbc.procurarLivrosPorNomeAutor("Fi")) {
			System.out.println(li.getTitulo());
			
		}
	}
	

}
