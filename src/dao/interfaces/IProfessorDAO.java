package dao.interfaces;

import java.util.List;

import entity.Professor;


public interface IProfessorDAO {

	public void cadastrarProfessor(Professor professor);
	
	public void removerProfessorPorID(int idProfessor);

	public List<Professor> listarTodosProfessores();
	
	public List<Professor> procurarPorNome(String nomeProfessor);
	
	public Professor procurarPorId(int idProfessor);

}
