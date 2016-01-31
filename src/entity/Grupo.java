package entity;

import dao.ProfessorJDBCDAO;

public class Grupo {
	private int id;
	private Professor professor;
	private String nome;
	
	public Grupo(Professor professor, String nome) {
		this.professor = professor;
		this.nome = nome;
	}
	
	public Grupo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(int idProfessor) {
		ProfessorJDBCDAO pjdbc = new ProfessorJDBCDAO();
		this.professor = pjdbc.procurarPorId(idProfessor);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
