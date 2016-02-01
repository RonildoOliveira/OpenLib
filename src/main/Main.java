package main;

import dao.GrupoJDBCDAO;
import dao.ProfessorJDBCDAO;
import dao.R_Livros_AreasJDBCDAO;
import dao.R_Livros_AutoresJDBCDAO;
import dao.UsuarioJDBCDAO;
import entity.Area;
import entity.Grupo;
import entity.Livro;
import entity.Usuario;
import entity.relations.Livros_Areas;

public class Main {
	public static void main(String[] args){
			
		UsuarioJDBCDAO userjdbc = new UsuarioJDBCDAO();
		userjdbc.cadastrarUsuario(new Usuario("Flavio","jose","jose@mail","jose.png","Musica"));
		userjdbc.removerUsuarioPorID(7);
		
		R_Livros_AutoresJDBCDAO liat_jdbc = new R_Livros_AutoresJDBCDAO();
		
//		for (Autor at : liat_jdbc.procurarAutoresPorNomeLivro("Dados")) {
//			System.out.println(at.getNome());
//			
//		}
		
//		for (Livro li : liat_jdbc.procurarLivrosPorNomeAutor("Fi")) {
//			System.out.println(li.getTitulo());
//			
//		}
		
		GrupoJDBCDAO gjdbc = new GrupoJDBCDAO();
//		for(Grupo g: gjdbc.listarTodosGrupos()){
//			System.out.println(g.getNome());
//		}
		
		if (gjdbc.procurarPorId(4)!=null){
			System.out.println(gjdbc.procurarPorId(4).getNome());
		}
		
		ProfessorJDBCDAO pjdbc = new ProfessorJDBCDAO();
		
		System.out.println(pjdbc.procurarPorId(1).getNome());
		
		gjdbc.cadastrarGrupo(new Grupo(pjdbc.procurarPorId(1), "GrupoFBD"));
		
		for(Grupo g: gjdbc.procurarPorNome("FBD")){
			System.out.println(g.getNome());
		}
		
		R_Livros_AreasJDBCDAO rLivroAreas = new R_Livros_AreasJDBCDAO();
		
			
		for (Livros_Areas lva : rLivroAreas.listarLivrosAreas()){
			System.out.println(lva.getAreaLivro().getNome());
		}
		
		for (Area lva : rLivroAreas.procurarAreaPorNomeLivro("Java")){
			System.out.println(lva.getNome());
		}
		
		for (Livro lva : rLivroAreas.procurarLivrosPorNomeArea("a")){
			System.out.println(lva.getTitulo());
		}
	}
	

}
