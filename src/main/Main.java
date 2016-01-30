package main;

import java.sql.Connection;
import java.util.Iterator;

import dao.AreaLivroJDBCDAO;
import dao.AutorJDBCDAO;
import dao.EditoraJDBCDAO;
import dao.LivroJDBCDAO;
import dao.ProfessorJDBCDAO;
import dao.UsuarioJDBCDAO;
import entity.AreaLivro;
import entity.Autor;
import entity.Editora;
import entity.Livro;
import entity.Professor;
import entity.Usuario;

public class Main {
	private static Connection conexao = null;
	public static void main(String[] args){
		
//		conexao = new ConnectionFactory().getConnection();
//		System.out.println("OPEN");
//		
//		try {
//			conexao.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ArrayList<Livro> arrayListLivro = new RepositorioLivros().lista();
//		for (Iterator iterator = arrayListLivro.iterator(); iterator.hasNext();) {
//			Livro livro = (Livro) iterator.next();
//			System.out.println(livro.getTitulo());
//		}
		
		LivroJDBCDAO jdbc = new LivroJDBCDAO();
		jdbc.cadastrarLivro(new Livro("Xadrez",2003,4,1,"www.link","www.down","Xadrez"));
		jdbc.removerLivroPorID(16);
		jdbc.removerLivroPorID(15);
		jdbc.removerLivroPorID(17);
	
		
		for (Livro livro : jdbc.procurarPorTitulo("droi")) {
			System.out.println(livro.getTitulo());
		}
		
		if(jdbc.procurarPorId(5) != null)
			System.out.println(jdbc.procurarPorId(5).getTitulo());
		
		AutorJDBCDAO atjdbc = new AutorJDBCDAO();
		atjdbc.cadastrarAutor(new Autor("Ronildo", 1));
		atjdbc.removerAutorPorID(4);
		
		
		jdbc.removerLivroPorID(5);
		jdbc.removerLivroPorID(1);
		
		atjdbc.cadastrarAutor(new Autor("Telebau", 0));
		atjdbc.cadastrarAutor(new Autor("Cu Rose", 0));
		atjdbc.cadastrarAutor(new Autor("Wesley Safadao", 0));
		
		for (Autor autor: atjdbc.procurarPorNome("N")) {
			System.out.println(autor.getNome());
			
		}
		
		if (atjdbc.procurarPorId(7) != null)
			System.out.println(atjdbc.procurarPorId(7).getNome());
		
		AreaLivroJDBCDAO alivro = new AreaLivroJDBCDAO();
		alivro.cadastrarArea(new AreaLivro("C++"));
		alivro.removerAreaPorID(1);
		for (AreaLivro area : alivro.listarTodasAreas()) {
			System.out.println(area.getNome());
			
		}
		
		if (alivro.procurarPorId(3) != null) {
			System.out.println(alivro.procurarPorId(3).getNome());
		}
		
		EditoraJDBCDAO edlivro = new EditoraJDBCDAO();
		edlivro.cadastrarEditora(new Editora("Ximbica Books"));
		edlivro.removerEditoraPorID(3);
		for(Editora ed : edlivro.listarTodasEditoras()){
			System.out.println(ed.getNome());
		}
		for(Editora ed : edlivro.procurarPorNome("R")){
			System.out.println(ed.getNome());
		}
		
		if (edlivro.procurarPorId(2) != null) {
			System.out.println(edlivro.procurarPorId(2).getNome());
		}
		
		//--------
		ProfessorJDBCDAO prjdbc = new ProfessorJDBCDAO();
		prjdbc.cadastrarProfessor(new Professor("dntas",0));
			
		System.out.println("por nome");
		for (Professor prof: prjdbc.procurarPorNome("H")) {
			System.out.println(prof.getNome());
			
		}
		
		if (prjdbc.procurarPorId(11) != null)
			System.out.println(prjdbc.procurarPorId(11).getNome());
		
		atjdbc.cadastrarAutor(new Autor("", 4));
		
		
		UsuarioJDBCDAO userjdbc = new UsuarioJDBCDAO();
		userjdbc.cadastrarUsuario(new Usuario("Flavio","jose","jose@mail","jose.png","Musica"));
		userjdbc.removerUsuarioPorID(7);
	}
	

}
