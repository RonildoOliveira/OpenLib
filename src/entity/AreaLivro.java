package entity;

public class AreaLivro {
	
	private int id;
	private String nome;
	
	public AreaLivro() {
	
	}
	
	public AreaLivro(String nome){
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
