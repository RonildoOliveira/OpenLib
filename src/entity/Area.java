package entity;

public class Area {
	
	private int id;
	private String nome;
	
	public Area() {
	
	}
	
	public Area(String nome){
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
